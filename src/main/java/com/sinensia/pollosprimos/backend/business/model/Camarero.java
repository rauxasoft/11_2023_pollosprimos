package com.sinensia.pollosprimos.backend.business.model;

public class Camarero extends Persona{
	private static final long serialVersionUID = 1L;
	
	private String licenciaManipuladorAlimentos;
	
	public Camarero() {
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
