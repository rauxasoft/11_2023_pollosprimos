package com.sinensia.pollosprimos.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.common.Pagina;

public interface CategoriaServices {

	Optional<Categoria> read(Long id);
	Long create(Categoria categoria);
	
	List<Categoria> getAll();
	
	Pagina<Categoria> getPagina(int pageNumber, int pageSize);
	
}
