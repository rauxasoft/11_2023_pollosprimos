package com.sinensia.pollosprimos.backend.auditoria.filtro;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinensia.pollosprimos.backend.auditoria.model.HttpAuditLog;
import com.sinensia.pollosprimos.backend.auditoria.services.HttpAuditLogServices;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Component
public class FiltroAuditor implements Filter{

	@Autowired
	private HttpAuditLogServices httpAuditLogServices;
	
	@Override
	@Transactional
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		Long entrada = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		HttpAuditLog auditLog = new HttpAuditLog();
		auditLog.setEntrada(entrada);
		auditLog.setRemoteIP(httpServletRequest.getRemoteAddr());
		auditLog.setRecurso(httpServletRequest.getRequestURI());
		auditLog.setVerbo(httpServletRequest.getMethod());
		
		auditLog.setHttpStatusCode(httpServletResponse.getStatus());
		auditLog.setElapsedTime(System.currentTimeMillis() - entrada);
		
		httpAuditLogServices.create(auditLog);
	}

}
