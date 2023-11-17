package com.sinensia.pollosprimos.backend.auditoria.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.auditoria.model.HttpAuditLog;
import com.sinensia.pollosprimos.backend.auditoria.services.HttpAuditLogServices;

@Service
public class HttpAuditLogServicesImpl implements HttpAuditLogServices {

	private final Map<Long, HttpAuditLog> AUDIT_LOGS = new HashMap<>();
	
	@Override
	public void create(HttpAuditLog httpAuditLog) {
		Long id = System.currentTimeMillis();
		httpAuditLog.setId(id);
		AUDIT_LOGS.put(id, httpAuditLog);
	}

	@Override
	public List<HttpAuditLog> getAll() {
		
		List<HttpAuditLog> httpAuditLogs = new ArrayList<>(AUDIT_LOGS.values());
		Collections.sort(httpAuditLogs, (a1, a2) -> a2.getId().compareTo(a1.getId()));
		
		return httpAuditLogs;
	}

}
