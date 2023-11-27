package com.sinensia.pollosprimos.backend.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;

@Controller
@RequestMapping("/app")
public class AppCategoriaController {

	@Autowired
	private CategoriaServices categoriaServices;
	
	@GetMapping("/categorias")
	public ModelAndView getListadoCaregorias(ModelAndView mav) {
	
		mav.addObject("categorias", categoriaServices.getAll());
		mav.setViewName("listado-categorias");
		
		return mav;
	}
}
