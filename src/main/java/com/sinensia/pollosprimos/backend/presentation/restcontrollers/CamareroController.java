package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.services.CamareroServices;
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
		
		try {
			Long id = camareroServices.create(camarero);
			
			URI uri = ucb.path("/camareros/{id}").build(id);
			
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			
			RespuestaErrorHttp respuesta = new RespuestaErrorHttp(e.getMessage());
			
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		Optional<Camarero> optional = camareroServices.read(id);
		
		if(optional.isEmpty()) {
			RespuestaErrorHttp respuesta = new RespuestaErrorHttp("No existe el camarero " + id);
			return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
		} else {
			Camarero camarero = optional.get();
			return ResponseEntity.ok(camarero);
		}
	
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Camarero camarero, @PathVariable Long id) {
			
		try {
			
			Optional<Camarero> optional = camareroServices.read(id);
			
			if (optional.isPresent()) {
				camareroServices.update(camarero);
				return ResponseEntity.noContent().build();
			} else {
				RespuestaErrorHttp respuesta = new RespuestaErrorHttp("El camarero " + id + " no existe. No se puede actualizar");
				return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			RespuestaErrorHttp respuesta = new RespuestaErrorHttp(e.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		try {
			camareroServices.delete(id);
			return ResponseEntity.noContent().build();
		} catch(Exception e) {
			RespuestaErrorHttp respuesta = new RespuestaErrorHttp(e.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
