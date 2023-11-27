package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;
import com.sinensia.pollosprimos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@RequestMapping(params = {"min", "max"}, method = RequestMethod.GET)
	public List<Producto> getBetweenPriceRange(@RequestParam Double min, @RequestParam Double max){ 
		return productoServices.getBetweenPriceRange(min, max);	
	}
	
	@GetMapping("/{codigo}")
	public Producto read(@PathVariable Long codigo) {
		
		Optional<Producto> optional = productoServices.read(codigo);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el producto " + codigo, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	
	@GetMapping
	public List<Producto> getAll(){
		return productoServices.getAll();
	}
	
}
