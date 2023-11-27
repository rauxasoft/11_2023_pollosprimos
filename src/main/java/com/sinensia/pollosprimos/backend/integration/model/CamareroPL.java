package com.sinensia.pollosprimos.backend.integration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="CAMAREROS")
@PrimaryKeyJoinColumn(name="CODIGO_CAMARERO")
public class CamareroPL extends PersonaPL{
	private static final long serialVersionUID = 1L;
	
	private String licenciaManipuladorAlimentos;
	
	public CamareroPL() {
		// No args constructor
	}
	
	public String getLicenciaManipuladorAlimentos() {
		return licenciaManipuladorAlimentos;
	}

	public void setLicenciaManipuladorAlimentos(String licenciaManipuladorAlimentos) {
		this.licenciaManipuladorAlimentos = licenciaManipuladorAlimentos;
	}



	@Override
	public String toString() {
		return "Camarero [toString()=" + super.toString() + "]";
	}
	
}
