package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;
import com.sinensia.pollosprimos.backend.common.PageToPaginaConverter;
import com.sinensia.pollosprimos.backend.common.Pagina;
import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.repositories.CategoriaPLPagingRepostiry;
import com.sinensia.pollosprimos.backend.integration.repositories.CategoriaPLRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaServicesImpl implements CategoriaServices {
	
	private CategoriaPLRepository categoriaPLRepository;
	private CategoriaPLPagingRepostiry categoriaPLPagingRepostiry;
	private DozerBeanMapper mapper;
	
	@Autowired
	public CategoriaServicesImpl(CategoriaPLRepository categoriaPLRepository,
								 CategoriaPLPagingRepostiry categoriaPLPagingRepostiry,
								 DozerBeanMapper mapper) {
		
		this.categoriaPLRepository = categoriaPLRepository;
		this.categoriaPLPagingRepostiry = categoriaPLPagingRepostiry;
		this.mapper = mapper;
	}
	
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
		
		CategoriaPL createdCategoriaPL = categoriaPLRepository.save(categoriaPL);
		
		return createdCategoriaPL.getId();
	}
	
	@Override
	public List<Categoria> getAll() {
		
		return categoriaPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Categoria.class))
				.toList();
	}
	
	@Override
	public Pagina<Categoria> getPagina(int pageNumber, int pageSize) {
		
		Page<CategoriaPL> page = categoriaPLPagingRepostiry.findAll(PageRequest.of(pageNumber, pageSize));
		
		PageToPaginaConverter<Categoria> conversor = new PageToPaginaConverter<>();
		
		List<Categoria> categorias = page.getContent().stream()
										.map(x -> mapper.map(x, Categoria.class))
										.toList();
		
		return conversor.convert(page, categorias);
		
	}

}
