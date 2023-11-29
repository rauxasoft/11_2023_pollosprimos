package com.sinensia.pollosprimos.backend.business.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.model.dtos.EstadisticaDTO1;
import com.sinensia.pollosprimos.backend.business.model.dtos.EstadisticaDTO2;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;
import com.sinensia.pollosprimos.backend.common.Pagina;
import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.model.ProductoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.ProductoPLRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServicesImpl implements ProductoServices {
	
	private ProductoPLRepository productoPLRepository;
	private DozerBeanMapper mapper;
	
	@Autowired
	public ProductoServicesImpl(ProductoPLRepository productoPLRepository, DozerBeanMapper mapper ) {
		this.productoPLRepository = productoPLRepository;
		this.mapper = mapper;
	}
	
	@Override
	@Transactional
	public Long create(Producto producto) {

		if(producto.getCodigo() != null) {
			throw new IllegalStateException("Para crear un producto el código ha de ser null");
		}
		
		ProductoPL productoPL = mapper.map(producto, ProductoPL.class);
		
		ProductoPL createdProductoPL = productoPLRepository.save(productoPL);
		
		return createdProductoPL.getCodigo();
	}

	@Override
	public Optional<Producto> read(Long codigo) {
		
		Producto producto = null;
		
		Optional<ProductoPL> optional = productoPLRepository.findById(codigo);
		
		if(optional.isPresent()) {
			producto = mapper.map(optional.get(), Producto.class);
		}
		
		return Optional.ofNullable(producto);
	}

	@Override
	@Transactional
	public void update(Producto producto) {
		
		Long codigo = producto.getCodigo();
		
		if(codigo == null) {
			throw new IllegalStateException("No se puede actualizar un producto con código null");
		}
		
		boolean existe = productoPLRepository.existsById(codigo);
		
		if(!existe) {
			throw new IllegalStateException("El producto " + codigo + " no existe. No se puede actualizar");
		}
		
		ProductoPL productoPL = mapper.map(producto, ProductoPL.class);
		
		productoPLRepository.save(productoPL);
		
	}

	@Override
	public List<Producto> getAll() {
		return convert(productoPLRepository.findAll());
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {		
		return convert(productoPLRepository.findByPrecioBetweenOrderByPrecio(min, max));
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) { 		
		return convert(productoPLRepository.findByFechaAltaBetweenOrderByFechaAlta(desde, hasta));
	}

	@Override
	public List<Producto> getDescatalogados() {
		return convert(productoPLRepository.findByDescatalogadoTrue());
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		
		CategoriaPL categoriaPL = mapper.map(categoria, CategoriaPL.class);
	
		return convert(productoPLRepository.findByCategoria(categoriaPL));
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoPLRepository.count();
	}

	@Override
	@Transactional
	public void variarPrecio(List<Producto> productos, double porcentaje) {
	
		List<ProductoPL> productosPL = productos.stream()
				.map(x -> mapper.map(x, ProductoPL.class))
				.toList();
		
		productoPLRepository.variarPrecio(productosPL, porcentaje);
		
	}

	@Override
	@Transactional
	public void variarPrecio(long[] codigos, double porcentaje) {
		productoPLRepository.variarPrecio(codigos, porcentaje);
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductoPorCategoria() {
		
		List<Object[]> resultSet = productoPLRepository.getEstadisticaNumeroProductos();
		
		Map<Categoria, Integer> estadistica = new HashMap<>();
		
		resultSet.stream().forEach(x -> {
			
			Categoria categoria = new Categoria();
			categoria.setId((Long) x[0]);
			categoria.setNombre((String) x[1]);
			estadistica.put(categoria, ((Long) x[2]).intValue());
		});
		
		return estadistica;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioProductosPorCategoria() {
		
		List<Object[]> resultados = productoPLRepository.getEstadisticaPrecioMedio();
		
		Map<Categoria, Double> estadistica = new HashMap<>();
		
		resultados.stream().forEach(x -> {
			
			Categoria categoria = new Categoria();
			categoria.setId((Long) x[0]);
			categoria.setNombre((String) x[1]);
			
			BigDecimal precioMediAsBigDecimal = (BigDecimal) x[2];
			Double precioMedio = precioMediAsBigDecimal != null ? precioMediAsBigDecimal.doubleValue() : null;
			precioMedio = precioMedio != null ? Math.round(precioMedio * 100.0) / 100.0 : null;
			
			estadistica.put(categoria, precioMedio);
		});
		
		return estadistica;
	}
	
	@Override
	public Pagina<Producto> getPagina(int pageNumnber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EstadisticaDTO1> getEstadisticasDTO1() {
		
		Map<Categoria, Integer> estadistica = getEstadisticaNumeroProductoPorCategoria();
		
		return estadistica.entrySet().stream().map(x -> { 
			
					Categoria categoria = x.getKey();
					Integer cantidad = x.getValue();
					
					return new EstadisticaDTO1(categoria.getId(), categoria.getNombre(), cantidad);
					
				}).toList();
	}
	
	@Override
	public List<EstadisticaDTO2> getEstadisticasDTO2() {
		
		Map<Categoria, Integer> estadistica = getEstadisticaNumeroProductoPorCategoria();
		
		return estadistica.entrySet().stream().map(x -> { 
			
					Categoria categoria = x.getKey();
					Integer cantidad = x.getValue();
			
					return new EstadisticaDTO2(categoria, cantidad);
				}).toList();
	}
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private List<Producto> convert(List<ProductoPL> productosPL){
		
		return productosPL.stream()
				.map(x -> mapper.map(x, Producto.class))
				.toList();
	}
	
}
