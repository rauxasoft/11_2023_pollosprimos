package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.services.CamareroServices;
import com.sinensia.pollosprimos.backend.presentation.config.PresentationException;
import com.sinensia.pollosprimos.backend.presentation.config.RespuestaErrorHttp;

@RestController
@RequestMapping("/camareros")
public class CamareroController {

	@Autowired
	private CamareroServices camareroServices;
	
	@GetMapping
	public List<Camarero> getAll(){
		return camareroServices.getAll();
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Camarero camarero, UriComponentsBuilder ucb) {
		
		Long id = null;
		
		try {
			id = camareroServices.create(camarero);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST); // RESPUESTA	
		}
				
		return ResponseEntity.created(ucb.path("/camareros/{id}").build(id))
								.build();
	}	
	
	@GetMapping("/{id}")
	public Camarero read(@PathVariable Long id) {
		
		Optional<Camarero> optional = camareroServices.read(id);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No se encuentra el camarero con id " + id, HttpStatus.NOT_FOUND); // RESPUESTA	
		} 
		
		return optional.get();		
	}
		
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Camarero camarero, @PathVariable Long id) {
		
		camarero.setId(id);
		
		try {
			camareroServices.update(camarero);
		} catch(IllegalArgumentException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST); // RESPUESTA	
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND); // RESPUESTA	
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		try {
			camareroServices.delete(id);	
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND); // RESPUESTA	
		}
	}
		
	// **************************************************************************************
	
	@ExceptionHandler(PresentationException.class)
	public ResponseEntity<?> gestionarPresentationException(PresentationException ex){
		RespuestaErrorHttp respuesta = new RespuestaErrorHttp(ex.getMessage());
		return new ResponseEntity<>(respuesta, ex.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> gestionarException (Exception ex) {
		RespuestaErrorHttp respuesta = new RespuestaErrorHttp(ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> gestionarExceptionparse (Exception ex) {
		RespuestaErrorHttp respuesta = new RespuestaErrorHttp(ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
	
}
