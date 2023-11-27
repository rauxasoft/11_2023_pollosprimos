package com.sinensia.pollosprimos.backend.integration.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class DatosContactoPL implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String telefono;
	private String fax;
	private String email;
	
	public DatosContactoPL() {
		// No args constructor
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "DatosContacto [telefono=" + telefono + ", fax=" + fax + ", email=" + email + "]";
	}

}
