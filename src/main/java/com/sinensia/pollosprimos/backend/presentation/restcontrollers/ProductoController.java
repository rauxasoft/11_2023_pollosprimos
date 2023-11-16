package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@RequestMapping(params = {"min", "max"}, method = RequestMethod.GET)
	public List<Producto> getBetweenPriceRange(@RequestParam Double min, @RequestParam Double max){ 
		return productoServices.getBetweenPriceRange(min, max);	
	}
	
	@GetMapping
	public List<Producto> getAll(){
		return productoServices.getAll();
	}
	
}
