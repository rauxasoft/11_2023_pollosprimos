package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;

@RestController
@RequestMapping("/pruebas")
public class BorrameController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/productos-incrementar")
	public String prueba6(){
		
		Producto p1 = new Producto();
		Producto p2 = new Producto();
		
		p1.setCodigo(100L);
		p2.setCodigo(102L);
		
		List<Producto> productos = Arrays.asList(p1, p2);
		
		productoServices.variarPrecio(productos, 50.0);
		
		return "ok";
	}
		
	@GetMapping("/productos-categoria")
	public List<Producto> prueba4(){
		
		Categoria categoria = new Categoria();
		categoria.setId(9L);
		
		return productoServices.getByCategoria(categoria);
	}
	
	@GetMapping("/productos-descatalogados")
	public List<Producto> prueba3(){
		return productoServices.getDescatalogados();
	}
	
	@GetMapping("/productos-filtrados-por-precio")
	public List<Producto> prueba2(){
		return productoServices.getBetweenPriceRange(2.5, 3.0);
	}
	
}
