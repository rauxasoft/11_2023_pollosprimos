package com.sinensia.pollosprimos.backend.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosprimos.backend.business.services.CamareroServices;

@Controller
@RequestMapping("/app")
public class AppCamareroController {

	@Autowired
	private CamareroServices camareroServices;
	
	@GetMapping("/camareros")
	public ModelAndView getListadoCamareros(ModelAndView mav) {
		
		mav.addObject("camareros", camareroServices.getAll());
		mav.setViewName("listado-camareros");
		
		return mav;
	}
}
