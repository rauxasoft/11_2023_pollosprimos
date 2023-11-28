package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Establecimiento;
import com.sinensia.pollosprimos.backend.business.services.EstablecimientoServices;
import com.sinensia.pollosprimos.backend.integration.model.EstablecimientoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.EstablecimientoPLRepository;

import jakarta.transaction.Transactional;

@Service
public class EstablecimientoServicesImpl implements EstablecimientoServices{

	private EstablecimientoPLRepository establecimientoPLRepository;	
	private DozerBeanMapper mapper;
	
	@Autowired
	public EstablecimientoServicesImpl(EstablecimientoPLRepository establecimientoPLRepository, DozerBeanMapper mapper) {
		this.establecimientoPLRepository = establecimientoPLRepository;
		this.mapper = mapper;
	}
	
	@Override
	@Transactional
	public Long create(Establecimiento establecimiento) {
		
		if(establecimiento.getCodigo() != null) {
			throw new IllegalStateException("Para crear un establecimiento el código ha de ser null");
		}
		
		EstablecimientoPL establecimientoPL = mapper.map(establecimiento, EstablecimientoPL.class);
		
		EstablecimientoPL createdEstablecimientoPL = establecimientoPLRepository.save(establecimientoPL);
		
		return createdEstablecimientoPL.getCodigo();
	}

	@Override
	public Optional<Establecimiento> read(Long codigo) {
	
		Establecimiento establecimiento = null;
		
		Optional<EstablecimientoPL> optional = establecimientoPLRepository.findById(codigo);
		
		if(optional.isPresent()) {
			establecimiento = mapper.map(optional.get(), Establecimiento.class);
		}
		
		return Optional.ofNullable(establecimiento);
	}

	@Override
	public List<Establecimiento> getAll() {
		
		return establecimientoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Establecimiento.class))
				.toList();
	}

}
