package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Cliente;
import com.sinensia.pollosprimos.backend.business.services.ClienteServices;

@Service
public class ClienteServicesImpl implements ClienteServices {

	@Override
	public List<Cliente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cliente> read(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
