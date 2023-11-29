package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;
import com.sinensia.pollosprimos.backend.common.Pagina;
import com.sinensia.pollosprimos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaServices categoriaServices; 
	
	@Autowired
	public CategoriaController(CategoriaServices categoriaServices) {
		this.categoriaServices = categoriaServices;
	}
	
	@GetMapping
	public List<Categoria> getAll(){ 
		return categoriaServices.getAll();
	}
	
	@GetMapping("/{id}")
	public Categoria read(@PathVariable Long id){ 
		
		Optional<Categoria> optional = categoriaServices.read(id);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe la categoría con id " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Categoria categoria, UriComponentsBuilder ucb) {
	
		Long id = null;
		
		try {
			id = categoriaServices.create(categoria);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);	
		}
				
		return ResponseEntity.created(ucb.path("/categorias/{id}").build(id))
								.build();
		
	}
	
	@GetMapping("/pagina")
	public Pagina<Categoria> getPagina(@RequestParam("page-number") Integer pageNumber,
									   @RequestParam("page-size") Integer pageSize){
		// TODO
		return null;
	}

}
