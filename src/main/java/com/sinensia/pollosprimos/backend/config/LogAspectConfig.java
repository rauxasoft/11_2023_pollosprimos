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

	@Before(value="execution(* com.sinensia.pollosprimos.backend.business.services.impl.*.*(..))")
	public void logBusinessLayer(JoinPoint joinPoint) {
		
		String nombreClase = joinPoint.getTarget().getClass().getSimpleName();
		String nombreMetodo = joinPoint.getSignature().getName();
		
		logger.info("Invocado método {}() de la clase {}", nombreMetodo, nombreClase);
	}
}
