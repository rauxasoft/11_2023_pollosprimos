package com.sinensia.pollosprimos.backend.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosprimos.backend.business.services.ProductoServices;

@Controller
@RequestMapping("/app")
public class AppProductoController {
	
	@Autowired
	private ProductoServices productoServices;

	@GetMapping("/productos")
	public ModelAndView getPaginaProductos(ModelAndView mav) {
		
		mav.addObject("productos", productoServices.getAll());
		mav.setViewName("listado-productos");
		
		return mav;
	}
}
