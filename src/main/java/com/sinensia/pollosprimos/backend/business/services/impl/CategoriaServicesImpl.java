package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;
import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.repositories.CategoriaPLRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaServicesImpl implements CategoriaServices {

	@Autowired
	private CategoriaPLRepository categoriaPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Optional<Categoria> read(Long id) {
		
		Optional<CategoriaPL> optional = categoriaPLRepository.findById(id);
		
		Categoria categoria = null;
		
		if(optional.isPresent()) {
			categoria = mapper.map(optional.get(), Categoria.class);
		}
		
		return Optional.ofNullable(categoria);
	}
	
	@Override
	@Transactional
	public Long create(Categoria categoria) {
		
		if(categoria.getId() != null) {
			throw new IllegalStateException("Para poder crear una categoria su id ha de ser null");
		}
		
		CategoriaPL categoriaPL = mapper.map(categoria, CategoriaPL.class);
		categoriaPL.setId(System.currentTimeMillis());
		CategoriaPL createdCategoriaPL = categoriaPLRepository.save(categoriaPL);
		
		return createdCategoriaPL.getId();
	}
	
	@Override
	public List<Categoria> getAll() {
		
		return categoriaPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Categoria.class))
				.collect(Collectors.toList());
	}
}
