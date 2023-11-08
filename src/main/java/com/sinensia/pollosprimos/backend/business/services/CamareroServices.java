package com.sinensia.pollosprimos.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosprimos.backend.business.model.Camarero;

public interface CamareroServices {

	/**
	 * Lanza IllegalStateException si el id no es null
	 * Lanza IllegalStateException si el DNI es null o ya existe un camarero con el mismo DNI
	 * 
	 * @return Devuelve el codigo otorgado por el sistema.
	 */
	Long create(Camarero camarero);				// C
	
	Optional<Camarero> read(Long id);			// R	
	Optional<Camarero> read(String dni);
	
	/**
	 * Lanza IllegalStateException si el id es null
	 * Lanza IllegalStateException so el id no existe en el sistema
	 * 
	 */
	void update(Camarero camarero);				// U
	
	List<Camarero> getAll();
	List<Camarero> getByNombreLikeIgnoreCase(String texto);
	
	int getNumeroTotalCamareros();

}
