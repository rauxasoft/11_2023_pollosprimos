package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Establecimiento;
import com.sinensia.pollosprimos.backend.business.services.EstablecimientoServices;
import com.sinensia.pollosprimos.backend.integration.repositories.EstablecimientoPLRepository;

@Service
public class EstablecimientoServicesImpl implements EstablecimientoServices{

	@Autowired
	private EstablecimientoPLRepository establecimientoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Long create(Establecimiento establecimiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Establecimiento> read(Long codigo) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Establecimiento> getAll() {
		
		return establecimientoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Establecimiento.class))
				.collect(Collectors.toList());
	}

}
