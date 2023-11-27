package com.sinensia.pollosprimos.backend.auditoria.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosprimos.backend.auditoria.services.HttpAuditLogServices;

@Controller
@RequestMapping("/app")
public class AppAuditoriaController {

	private HttpAuditLogServices httpAuditLogServices;
	
	@Autowired
	public AppAuditoriaController(HttpAuditLogServices httpAuditLogServices) {
		this.httpAuditLogServices = httpAuditLogServices;
	}
	
	@GetMapping("/logs")
	public ModelAndView getPaginaLogs(ModelAndView mav) {
		
		mav.addObject("logs", httpAuditLogServices.getAll());
		mav.setViewName("auditoria/listado-log");
		
		return mav;
	}
}
