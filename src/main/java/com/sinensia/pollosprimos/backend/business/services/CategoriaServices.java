package com.sinensia.pollosprimos.backend.business.services;

import java.util.List;

import com.sinensia.pollosprimos.backend.business.model.Categoria;

public interface CategoriaServices {

	Long create(Categoria categoria);
	
	List<Categoria> getAll();
	
}
