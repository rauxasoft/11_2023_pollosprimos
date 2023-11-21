package com.sinensia.pollosprimos.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosprimos.backend.business.model.Categoria;

public interface CategoriaServices {

	Optional<Categoria> read(Long id);
	Long create(Categoria categoria);
	
	List<Categoria> getAll();
	
}
