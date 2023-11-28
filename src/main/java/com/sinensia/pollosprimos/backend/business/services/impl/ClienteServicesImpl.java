package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Cliente;
import com.sinensia.pollosprimos.backend.business.services.ClienteServices;
import com.sinensia.pollosprimos.backend.integration.repositories.ClientePLRepository;

@Service
public class ClienteServicesImpl implements ClienteServices {

	private ClientePLRepository clientePLRepository;
	
	private DozerBeanMapper mapper;
	
	@Autowired
	public ClienteServicesImpl(ClientePLRepository clientePLRepository, DozerBeanMapper mapper) {
		this.clientePLRepository = clientePLRepository;
		this.mapper = mapper;
	}
	
	@Override
	public List<Cliente> getAll() {
	
		return clientePLRepository.findAll().stream()
				.map(x -> mapper.map(x, Cliente.class))
				.toList();
	
	}

	@Override
	public Optional<Cliente> read(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
