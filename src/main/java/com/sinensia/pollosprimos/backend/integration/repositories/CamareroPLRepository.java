package com.sinensia.pollosprimos.backend.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.pollosprimos.backend.integration.model.CamareroPL;

public interface CamareroPLRepository extends JpaRepository<CamareroPL, Long>{

	boolean existsByDni(String dni);
	
	CamareroPL findByDni(String dni);

	@Query("SELECT c FROM CamareroPL c WHERE UPPER(c.nombre) LIKE UPPER(CONCAT('%',:texto,'%'))")
	List<CamareroPL> findByNombreLikeIgnoreCase(String texto);
	
}
