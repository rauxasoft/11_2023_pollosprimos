package com.sinensia.pollosprimos.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.pollosprimos.backend.business.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
