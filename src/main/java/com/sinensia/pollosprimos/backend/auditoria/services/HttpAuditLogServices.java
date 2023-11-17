package com.sinensia.pollosprimos.backend.auditoria.services;

import java.util.List;

import com.sinensia.pollosprimos.backend.auditoria.model.HttpAuditLog;

public interface HttpAuditLogServices {

	void create(HttpAuditLog httpAuditLog);
	
	/**
	 * Devuelve la lista en orden descendente. Primero los logs más recientes
	 * 
	 */
	List<HttpAuditLog> getAll();
}
