package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;
import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.model.ProductoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.ProductoPLRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoServicesImpl implements ProductoServices {

	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Long create(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Producto> read(Long codigo) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Producto producto) {
		
		// LUEGO
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		
		List<ProductoPL> productosPL = productoPLRepository.findByPrecioBetweenOrderByPrecio(min, max);
		
		return productosPL.stream()
				.map(x -> mapper.map(x, Producto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		
		List<ProductoPL> productosPL = productoPLRepository.findByFechaAltaBetweenOrderByFechaAlta(desde, hasta);
		
		return productosPL.stream()
				.map(x -> mapper.map(x, Producto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> getDescatalogados() {
		
		List<ProductoPL> productosPL = productoPLRepository.findByDescatalogadoTrue();
		
		return productosPL.stream()
				.map(x -> mapper.map(x, Producto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		
		CategoriaPL categoriaPL = mapper.map(categoria, CategoriaPL.class);
		
		List<ProductoPL> productosPL = productoPLRepository.findByCategoria(categoriaPL);
		
		return productosPL.stream()
				.map(x -> mapper.map(x, Producto.class))
				.collect(Collectors.toList());
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
				.collect(Collectors.toList());
		
		productoPLRepository.variarPrecio(productosPL, porcentaje);
		
	}

	@Override
	public void variarPrecio(long[] codigos, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductoPorCategoria() {
		
		// LUEGO
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioProductosPorCategoria() {
		
		// LUEGO
		
		// TODO Auto-generated method stub
		return null;
	}

}
