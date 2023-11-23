package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.services.CamareroServices;
import com.sinensia.pollosprimos.backend.integration.model.CamareroPL;
import com.sinensia.pollosprimos.backend.integration.repositories.CamareroPLRepository;

import jakarta.transaction.Transactional;

@Service
public class CamareroServicesImpl implements CamareroServices {

	@Autowired
	private CamareroPLRepository camareroPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public Long create(Camarero camarero) {
		
		if(camarero.getId() != null) {
			throw new IllegalStateException("Para crear un camarero el id ha de ser null");
		}
		
		String dni = camarero.getDni();
		
		if(dni == null) {
			throw new IllegalStateException("No se puede crear un camarero con DNI null");
		}
		
		boolean existe = camareroPLRepository.existsByDni(dni);
		
		if(existe) {
			throw new IllegalStateException("No se puede crear el camarero. Ya existe un camarero con DNI " + dni);
		}
		
		CamareroPL camareroPL = mapper.map(camarero, CamareroPL.class);
		
		CamareroPL createdCamareroPL = camareroPLRepository.save(camareroPL);
		
		return createdCamareroPL.getId();
	}

	@Override
	public Optional<Camarero> read(Long id) {
		
		Optional<CamareroPL> optional = camareroPLRepository.findById(id);
		
		Camarero camarero = null;
		
		if(optional.isPresent()) {
			camarero = mapper.map(optional.get(), Camarero.class);
		}
		
		return Optional.ofNullable(camarero);
	}

	@Override
	public Optional<Camarero> read(String dni) {
		
		CamareroPL camareroPL = camareroPLRepository.findByDni(dni);
		
		Camarero camarero = null;
		
		if(camareroPL != null) {
			camarero = mapper.map(camareroPL, Camarero.class);
		}
	
		return Optional.ofNullable(camarero);
	}

	@Override
	@Transactional
	public void update(Camarero camarero) {
		
		Long id = camarero.getId();
		
		if(id == null) {
			throw new IllegalStateException("No se puede actualizar un camarero con id null");
		}
		
		boolean existe = camareroPLRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalStateException("El camarero " + id + " no existe. No se puede actualizar");
		}
		
		CamareroPL camareroPL = mapper.map(camarero, CamareroPL.class);
		
		camareroPLRepository.save(camareroPL);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		boolean existe = camareroPLRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalStateException("El camarero con id " + id + " no existe. No se puede eliminar");
		}
		
		camareroPLRepository.deleteById(id);
	}

	@Override
	public List<Camarero> getAll() {
		
		return camareroPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Camarero.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Camarero> getByNombreLikeIgnoreCase(String texto) {
		
		return camareroPLRepository.findByNombreLikeIgnoreCase(texto).stream()
				.map(x -> mapper.map(x, Camarero.class))
				.collect(Collectors.toList());
	}

	@Override
	public int getNumeroTotalCamareros() {
		return (int) camareroPLRepository.count();
	}

}
