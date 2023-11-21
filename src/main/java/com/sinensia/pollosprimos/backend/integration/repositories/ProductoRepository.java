package com.sinensia.pollosprimos.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.pollosprimos.backend.business.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
