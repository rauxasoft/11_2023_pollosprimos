package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaServices categoriaServices; 
	
	@GetMapping("/categorias")
	public List<Categoria> getAll(){ 
		return categoriaServices.getAll();
	}
	
	@PostMapping("/categorias")
	public String create(@RequestBody Categoria categoria) {
		Long id = categoriaServices.create(categoria);
		return "Se ha creado la caregoria " + id;
	}
}
