package com.sinensia.pollosprimos.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosprimos.backend.business.model.Cliente;

public interface ClienteServices {

	List<Cliente> getAll();
	
	Optional<Cliente> read(Long id);
}
