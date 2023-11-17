package com.sinensia.pollosprimos.backend.auditoria.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.auditoria.model.HttpAuditLog;
import com.sinensia.pollosprimos.backend.auditoria.services.HttpAuditLogServices;

@RestController
@RequestMapping("/http-logs")
public class HttpAuditLogController {

	@Autowired
	private HttpAuditLogServices httpAuditLogServices;
	
	@GetMapping
	public List<HttpAuditLog> getAll(){
		return httpAuditLogServices.getAll();
	}
	
}
