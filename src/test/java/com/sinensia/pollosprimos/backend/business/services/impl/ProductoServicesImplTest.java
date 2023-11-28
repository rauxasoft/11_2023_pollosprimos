package com.sinensia.pollosprimos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.model.dtos.EstadisticaDTO1;
import com.sinensia.pollosprimos.backend.business.model.dtos.EstadisticaDTO2;
import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.model.ProductoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.ProductoPLRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServicesImplTest {

	@Mock
	private ProductoPLRepository productoPLRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@InjectMocks
	private ProductoServicesImpl productoServicesImpl;
	
	private ProductoPL productoPL1;
	private ProductoPL productoPL2;
	private Producto producto1;
	private Producto producto2;

	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void create_producto_ok() {
		
		producto1.setCodigo(null);
		productoPL1.setCodigo(null);
		
		ProductoPL createdProductoPL = new ProductoPL();
		createdProductoPL.setCodigo(666L);
		
		when(mapper.map(producto1, ProductoPL.class)).thenReturn(productoPL1);
		when(productoPLRepository.save(productoPL1)).thenReturn(createdProductoPL);
		
		Long codigo = productoServicesImpl.create(producto1);
		
		assertEquals(666L, codigo);
	
	}
	
	@Test
	void create_producto_con_id_NO_null() {
	
		producto1.setCodigo(1000L);
		
		Exception exception = assertThrows(IllegalStateException.class, ()->{
			productoServicesImpl.create(producto1);
		});
		
		assertEquals("Para crear un producto el código ha de ser null", exception.getMessage());
	}
	
	@Test
	void read_producto_existente() {
		
		when(productoPLRepository.findById(1L)).thenReturn(Optional.of(productoPL1));
		when(mapper.map(productoPL1, Producto.class)).thenReturn(producto1);
		
		Optional<Producto> optional = productoServicesImpl.read(1L);
		
		assertTrue(optional.isPresent());
		assertEquals(producto1, optional.get());
	}
	
	@Test
	void read_producto_NO_existente() {
		
		when(productoPLRepository.findById(100L)).thenReturn(Optional.empty());
		
		Optional<Producto> optional = productoServicesImpl.read(100L);
		
		assertTrue(optional.isEmpty());
	}
	
	@Test
	void update_ok() {
		
		producto1.setCodigo(1L);
		
		when(productoPLRepository.existsById(1L)).thenReturn(true);
		when(mapper.map(producto1, ProductoPL.class)).thenReturn(productoPL1);
		
		productoServicesImpl.update(producto1);
		
		verify(productoPLRepository, times(1)).save(productoPL1);
	}
	
	@Test
	void update_con_codigo_null() {
		
		producto1.setCodigo(null);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServicesImpl.update(producto1);
		});
		
		assertEquals("No se puede actualizar un producto con código null", exception.getMessage());
		
	}
	
	@Test
	void update_producto_NO_existente() {
		
		producto1.setCodigo(1L);
		
		when(productoPLRepository.existsById(1L)).thenReturn(false);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServicesImpl.update(producto1);
		});
		
		assertEquals("El producto 1 no existe. No se puede actualizar", exception.getMessage());
	}
	
	@Test
	void getAll() {
		
		List<ProductoPL> productosPL = List.of(productoPL1, productoPL2);
		
		when(productoPLRepository.findAll()).thenReturn(productosPL);
		when(mapper.map(productoPL1, Producto.class)).thenReturn(producto1);
		when(mapper.map(productoPL2, Producto.class)).thenReturn(producto2);
		
		List<Producto> productos = productoServicesImpl.getAll();
		
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(List.of(producto1, producto2)));
	
	}
	
	@Test
	void getBetweenPriceRange() {
		
		List<ProductoPL> productosPL = List.of(productoPL1, productoPL2);
		
		when(productoPLRepository.findByPrecioBetweenOrderByPrecio(10.0, 20.0)).thenReturn(productosPL);
		when(mapper.map(productoPL1, Producto.class)).thenReturn(producto1);
		when(mapper.map(productoPL2, Producto.class)).thenReturn(producto2);
		
		List<Producto> productos = productoServicesImpl.getBetweenPriceRange(10.0, 20.0);
		
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(List.of(producto1, producto2)));
		
	}
	
	@Test
	void getBetweenDates() {
		
		List<ProductoPL> productosPL = List.of(productoPL1, productoPL2);
		
		Date desde = new Date(0);
		Date hasta = new Date(10);
		
		when(productoPLRepository.findByFechaAltaBetweenOrderByFechaAlta(desde, hasta)).thenReturn(productosPL);
		when(mapper.map(productoPL1, Producto.class)).thenReturn(producto1);
		when(mapper.map(productoPL2, Producto.class)).thenReturn(producto2);
		
		List<Producto> productos = productoServicesImpl.getBetweenDates(desde, hasta);
		
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(List.of(producto1, producto2)));	
	}
	
	@Test
	void getDescatalogados() {
		
		List<ProductoPL> productosPL = List.of(productoPL1, productoPL2);
		
		when(productoPLRepository.findByDescatalogadoTrue()).thenReturn(productosPL);
		when(mapper.map(productoPL1, Producto.class)).thenReturn(producto1);
		when(mapper.map(productoPL2, Producto.class)).thenReturn(producto2);
		
		List<Producto> productos = productoServicesImpl.getDescatalogados();
		
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(List.of(producto1, producto2)));	
		
	}
	
	@Test
	void getByCategoria() {
		
		List<ProductoPL> productosPL = List.of(productoPL1, productoPL2);
		
		Categoria categoria = new Categoria();
		CategoriaPL categoriaPL = new CategoriaPL();
		
		when(mapper.map(categoria, CategoriaPL.class)).thenReturn(categoriaPL);
		when(productoPLRepository.findByCategoria(categoriaPL)).thenReturn(productosPL);
		when(mapper.map(productoPL1, Producto.class)).thenReturn(producto1);
		when(mapper.map(productoPL2, Producto.class)).thenReturn(producto2);
		
		List<Producto> productos = productoServicesImpl.getByCategoria(categoria);
		
		assertEquals(2, productos.size());
		assertTrue(productos.containsAll(List.of(producto1, producto2)));	
	
	}
	
	@Test
	void getNumeroTotalProductos() {
		
		when(productoPLRepository.count()).thenReturn(4218L);
		
		int numeroTotalProductos = productoServicesImpl.getNumeroTotalProductos();
		
		assertEquals(4218, numeroTotalProductos);
	}
	
	@Test
	void variarPrecio_from_productos() {
		
		List<Producto> productos = List.of(producto1, producto2);
		
		when(mapper.map(producto1, ProductoPL.class)).thenReturn(productoPL1);
		when(mapper.map(producto2, ProductoPL.class)).thenReturn(productoPL2);
		
		productoServicesImpl.variarPrecio(productos, 50.0);
		
		verify(productoPLRepository, times(1)).variarPrecio(List.of(productoPL1, productoPL2), 50.0);
		
	}
	
	@Test
	void variarPrecio_from_codigos() {

		long[] codigos = {10L, 20L};
		
		productoServicesImpl.variarPrecio(codigos, 20.0);
		
		verify(productoPLRepository, times(1)).variarPrecio(codigos, 20.0);
	}
	
	@Test
	void getEstadisticaNumeroProductoPorCategoria() {
		
		Object[] fila1 = {1L, "CATEGORIA1", 10L};
		Object[] fila2 = {2L, "CATEGORIA2", 7L};
		
		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		categoria1.setId(1L);
		categoria2.setId(2L);
	
		List<Object[]> resultSet = List.of(fila1, fila2);
		
		when(productoPLRepository.getEstadisticaNumeroProductos()).thenReturn(resultSet);
		
		Map<Categoria, Integer> estadistica = productoServicesImpl.getEstadisticaNumeroProductoPorCategoria();
		
		assertEquals(2, estadistica.size());
		assertEquals(10, estadistica.get(categoria1));
		assertEquals(7, estadistica.get(categoria2));
	}
	
	@Test
	void getEstadisticaPrecioMedioProductosPorCategoria() {
		
		Object[] fila1 = {1L, "CATEGORIA1", new BigDecimal(7.69222)};
		Object[] fila2 = {2L, "CATEGORIA2", new BigDecimal(14.0023)};
		Object[] fila3 = {3L, "CATEGORIA3", null};
		
		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		Categoria categoria3 = new Categoria();
		categoria1.setId(1L);
		categoria2.setId(2L);
		categoria3.setId(3L);
	
		List<Object[]> resultSet = List.of(fila1, fila2, fila3);
		
		when(productoPLRepository.getEstadisticaPrecioMedio()).thenReturn(resultSet);
		
		Map<Categoria, Double> estadistica = productoServicesImpl.getEstadisticaPrecioMedioProductosPorCategoria();
		
		assertEquals(3, estadistica.size());
		assertEquals(7.69, estadistica.get(categoria1));
		assertEquals(14.00, estadistica.get(categoria2));
		assertNull(estadistica.get(categoria3));
	}
	
	@Test
	void getEstadisticasDTO1() {
		
		Object[] fila1 = {1L, "CATEGORIA1", 10L};
		Object[] fila2 = {2L, "CATEGORIA2", 7L};
		
		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		categoria1.setId(1L);
		categoria2.setId(2L);
	
		List<Object[]> resultSet = List.of(fila1, fila2);
		
		when(productoPLRepository.getEstadisticaNumeroProductos()).thenReturn(resultSet);
		
		List<EstadisticaDTO1> resultado = productoServicesImpl.getEstadisticasDTO1();
		
		List<Long> codigos = resultado.stream().map(x -> x.getId()).collect(Collectors.toList());
		
		// TODO Pendiente de testear que el DTO1 me viene con las cantidades correctas.
		assertEquals(2, resultado.size());
		assertTrue(codigos.containsAll(List.of(1L, 2L)));
		
	}
	
	@Test
	void getEstadisticasDTO2() {
		
		Object[] fila1 = {1L, "CATEGORIA1", 10L};
		Object[] fila2 = {2L, "CATEGORIA2", 7L};
		
		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		categoria1.setId(1L);
		categoria2.setId(2L);
	
		List<Object[]> resultSet = List.of(fila1, fila2);
		
		when(productoPLRepository.getEstadisticaNumeroProductos()).thenReturn(resultSet);
		
		List<EstadisticaDTO2> resultado = productoServicesImpl.getEstadisticasDTO2();
		
		List<Categoria> categorias = resultado.stream().map(x -> x.getCategoria()).collect(Collectors.toList());
		
		// TODO Pendiente de testear que el DTO1 me viene con las cantidades correctas.
		assertEquals(2, resultado.size());
		assertTrue(categorias.containsAll(List.of(categoria1, categoria2)));
	}
		
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private void initObjects() {
		
		productoPL1 = new ProductoPL();
		productoPL2 = new ProductoPL();
		
		producto1 = new Producto();
		producto2 = new Producto();
	}
}
