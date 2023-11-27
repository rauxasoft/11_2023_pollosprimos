package com.sinensia.pollosprimos.backend.business.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Direccion direccion;
	private DatosContacto datosContacto;
	
	public Persona() {
		// No args constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public DatosContacto getDatosContacto() {
		return datosContacto;
	}

	public void setDatosContacto(DatosContacto datosContacto) {
		this.datosContacto = datosContacto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Persona other = (Persona) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", direccion=" + direccion + ", datosContacto=" + datosContacto + "]";
	}

}
