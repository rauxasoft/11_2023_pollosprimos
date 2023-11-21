package com.sinensia.pollosprimos.backend.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.model.ProductoPL;

public interface ProductoPLRepository extends JpaRepository<ProductoPL, Long>{

	List<ProductoPL> findByPrecioBetweenOrderByPrecio(double min, double max);
	
	List<ProductoPL> findByFechaAltaBetweenOrderByFechaAlta(Date desde, Date hasta);
	
	List<ProductoPL> findByDescatalogadoTrue(); 
	
	List<ProductoPL> findByDescatalogado(boolean descatalogado); 

	List<ProductoPL> findByCategoria(CategoriaPL categoriaPL);
	
	@Query("UPDATE ProductoPL p SET p.precio = p.precio + (p.precio * :porcentaje / 100) WHERE p IN :productos")
	@Modifying
	int variarPrecio(List<ProductoPL> productos, double porcentaje);
	
}
