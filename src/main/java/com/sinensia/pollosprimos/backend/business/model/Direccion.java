package com.sinensia.pollosprimos.backend.business.model;

import java.io.Serializable;

public class Direccion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String domicilio;
	private String poblacion;
	private String codigoPostal;
	private String provincia;
	private String pais;
	
	public Direccion() {
		// No args constructor
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Direccion [domicilio=" + domicilio + ", poblacion=" + poblacion + ", codigoPostal=" + codigoPostal
				+ ", provincia=" + provincia + ", pais=" + pais + "]";
	}

}
