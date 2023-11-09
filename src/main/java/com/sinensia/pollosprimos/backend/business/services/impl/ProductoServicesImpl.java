package com.sinensia.pollosprimos.backend.business.services.impl;

import java.text.SimpleDateFormat;
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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
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
		Date fecha4 = null;
		Date fecha5 = null;
		
		try {
			fecha1 = sdf.parse("01/01/2015");
			fecha2 = sdf.parse("02/01/2015");
			fecha3 = sdf.parse("03/01/2015");
			fecha4 = sdf.parse("04/01/2015");
			fecha5 = sdf.parse("05/01/2015");
		} catch(Exception e) {
			
		}
		
		Producto producto1 = new Producto();
		producto1.setCodigo(100L);
		producto1.setFechaAlta(fecha1);
		
		
		// TODO Auto-generated method stub
		
	}
	
}
