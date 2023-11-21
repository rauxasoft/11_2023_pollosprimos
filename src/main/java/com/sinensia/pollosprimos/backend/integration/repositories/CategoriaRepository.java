package com.sinensia.pollosprimos.backend.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.pollosprimos.backend.business.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
