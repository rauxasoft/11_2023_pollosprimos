package com.sinensia.pollosprimos.backend.business.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;

public class ProductoServicesImpl implements ProductoServices{

	private final Map<Long, Producto> PRODUCTOS = new HashMap<>();
	
	public ProductoServicesImpl() {
		initObjects();
	}

	@Override
	public Long create(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Producto> read(Long codigo) {
		return Optional.ofNullable(PRODUCTOS.get(codigo));
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> getAll() {
		return new ArrayList<>(PRODUCTOS.values());
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getDescatalogados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTotalProductos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void variarPrecio(List<Producto> productos, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void variarPrecio(long[] codigos, double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Categoria, Integer> getEstadisticaNumeroProductoPorCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Categoria, Double> getEstadisticaPrecioMedioProductosPorCategoria() {
		
		Map<Categoria, Double> estadistica = new HashMap<>();
		Map<Categoria, Double> preciosAcumulados = new HashMap<>();
		Map<Categoria, Integer> numeroProductos = new HashMap<>();
		
		for(Producto producto: PRODUCTOS.values()) {
			
			Categoria categoria = producto.getCategoria();
			double precio = producto.getPrecio();
			
			if(numeroProductos.containsKey(categoria)) {
				numeroProductos.replace(categoria, numeroProductos.get(categoria) + 1);
				preciosAcumulados.replace(categoria, preciosAcumulados.get(categoria) + precio);
			} else {
				numeroProductos.put(categoria, 1);
				preciosAcumulados.put(categoria, precio);
			}
			
		}
		
		for(Categoria categoria: preciosAcumulados.keySet()) {
			estadistica.put(categoria, preciosAcumulados.get(categoria) / numeroProductos.get(categoria));
		}
		
		return estadistica;
	}
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private void initObjects() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha1 = null;
		Date fecha2 = null;
		Date fecha3 = null;
		
		Categoria c1 = new Categoria();
		Categoria c2 = new Categoria();
		
		c1.setId(1000L);
		c2.setId(1001L);
		c1.setNombre("TAPAS");
		c2.setNombre("REFRESCOS");
		
		try {
			fecha1 = sdf.parse("01/01/2015");
			fecha2 = sdf.parse("02/01/2015");
			fecha3 = sdf.parse("03/01/2015");
		} catch(Exception e) {
			
		}
		
		Producto producto1 = new Producto();
		producto1.setCodigo(101L);
		producto1.setFechaAlta(fecha1);
		producto1.setCategoria(c1);
		producto1.setNombre("Patatas Bravas de la Barceloneta");
		producto1.setPrecio(4.5);
		producto1.setDescripcion("Deliciosas patatas al punto picante. Pican de verdad!");
	
		Producto producto2 = new Producto();
		producto2.setCodigo(102L);
		producto2.setFechaAlta(fecha2);
		producto2.setCategoria(c1);
		producto2.setNombre("Bomba de la Barceloneta");
		producto2.setPrecio(6.5);
		producto2.setDescripcion("Deliciosas patatas rellenas con carne muy picante. Pican de verdad!");
		producto2.setDescatalogado(true);
		
		Producto producto3 = new Producto();
		producto3.setCodigo(103L);
		producto3.setFechaAlta(fecha3);
		producto3.setCategoria(c2);
		producto3.setNombre("Cocacola Zero");
		producto3.setPrecio(4.5);
		producto3.setDescripcion("Cocacola Zero sin azucar");
		
		PRODUCTOS.put(producto1.getCodigo(), producto1);
		PRODUCTOS.put(producto2.getCodigo(), producto2);
		PRODUCTOS.put(producto3.getCodigo(), producto3);
		
	}
	
}
