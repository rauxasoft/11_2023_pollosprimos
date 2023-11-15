package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;

public class CategoriaServicesImpl implements CategoriaServices {

	private final TreeMap<Long, Categoria> CATEGORIAS = new TreeMap<>();
	
	public CategoriaServicesImpl() {
		initObjects();	
	}
	
	@Override
	public Long create(Categoria categoria) {
		 
		Long id = CATEGORIAS.lastKey() + 1;
		categoria.setId(id);
		
		CATEGORIAS.put(id, categoria);
		
		return id;
	}

	@Override
	public List<Categoria> getAll() {
		return new ArrayList<>(CATEGORIAS.values());
	}
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private void initObjects() {
		
		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		Categoria categoria3 = new Categoria();
		
		categoria1.setId(100L);
		categoria1.setNombre("BEBIDAS");
		
		categoria2.setId(101L);
		categoria2.setNombre("TAPAS");
		
		categoria3.setId(102L);
		categoria3.setNombre("POSTRES");
		
		CATEGORIAS.put(categoria1.getId(), categoria1);
		CATEGORIAS.put(categoria2.getId(), categoria2);
		CATEGORIAS.put(categoria3.getId(), categoria3);
	}

}
