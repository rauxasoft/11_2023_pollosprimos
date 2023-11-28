package com.sinensia.pollosprimos.backend.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LogAspectConfig {
	
	private Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);

	@Before(value="execution(* com.sinensia.pollosprimos.backend.presentation.restcontrollers.*.*(..))")
	public void logPresentationLayer(JoinPoint joinPoint) {
		generarLog(joinPoint);
	}
	
	@Before(value="execution(* com.sinensia.pollosprimos.backend.business.services.impl.*.*(..))")
	public void logBusinessLayer(JoinPoint joinPoint) {
		generarLog(joinPoint);
	}	
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private void generarLog(JoinPoint joinPoint) {
		
		String nombreClase = joinPoint.getTarget().getClass().getSimpleName();
		String nombreMetodo = joinPoint.getSignature().getName();
		Object[] argumentos = joinPoint.getArgs();
		
		logger.info("Invocado método {}() de la clase {} con argumentos {}", nombreMetodo, nombreClase, argumentos);
	}
}
