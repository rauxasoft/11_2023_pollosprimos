package com.sinensia.pollosprimos.backend.presentation.config;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestorCentralizadoExcepciones extends ResponseEntityExceptionHandler {

	// ************************************************************************************************
	
	@ExceptionHandler(PresentationException.class)
	protected ResponseEntity<?> handlePresentationException(PresentationException ex, WebRequest request){
		RespuestaError respuestaError = new RespuestaError(ex.getMessage());
		return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), ex.getHttpStatus(), request);
	}
	
	// ************************************************************************************************
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> handleGenericException(Exception ex, WebRequest request){
		RespuestaError respuestaError = new RespuestaError(ex.getMessage());
		return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	// ************************************************************************************************
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Object valor = ex.getValue();
		String tipoEntrante = valor.getClass().getName();
		String tipoRequerido = (ex.getRequiredType()).getName();
		
		RespuestaError respuestaError = new RespuestaError("El parámetro " + valor + " es de tipo " + tipoEntrante + " - Se requiere un parámetro de tipo " + tipoRequerido);
		
		return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	// ************************************************************************************************

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return handleExceptionInternal(ex, new RespuestaError(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	// ************************************************************************************************
	
	
	
}
