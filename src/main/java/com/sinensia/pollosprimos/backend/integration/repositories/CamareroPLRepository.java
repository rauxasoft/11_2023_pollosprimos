package com.sinensia.pollosprimos.backend.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.pollosprimos.backend.integration.model.CamareroPL;

public interface CamareroPLRepository extends JpaRepository<CamareroPL, Long>{

	boolean existsByDni(String dni);
	
	CamareroPL findByDni(String dni);
	
	// Consultas con  JPQL
	
	@Query("SELECT c FROM CamareroPL c WHERE UPPER(c.nombre) LIKE UPPER(CONCAT('%',:texto,'%'))")
	List<CamareroPL> findByNombreLikeIgnoreCase(String texto);
	
	@Query("SELECT c FROM CamareroPL c")
	List<CamareroPL> dameTodos();
	
	@Query("SELECT c FROM CamareroPL c WHERE c.datosContacto.telefono = :telefono")
	List<CamareroPL> damePorTelefono(String telefono);
	
	@Query("SELECT c.nombre, c.apellido1, c.apellido2, c.datosContacto FROM CamareroPL c")
	List<Object[]> dameAtributosSueltos1();
	
	@Query("SELECT CONCAT(c.apellido1,' ',COALESCE(c.apellido2,''),', ',c.nombre), c.datosContacto FROM CamareroPL c")
	List<Object[]> dameAtributosSueltos2();
	
	@Query("SELECT LENGTH(c.nombre), 2 + 2 , UPPER(c.nombre) FROM CamareroPL c")
	List<Object[]> dameAtributosSueltos3();
	
	
}
