package com.sinensia.pollosprimos.backend.integration.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="PERSONAS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class PersonaPL implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CODIGO")
	@GeneratedValue(generator="PERSONA_SEQ")
	private Long id;
	
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	@Embedded
	private DireccionPL direccion;
	
	@Embedded
	private DatosContactoPL datosContacto;
	
	public PersonaPL() {
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

	public DireccionPL getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionPL direccion) {
		this.direccion = direccion;
	}

	public DatosContactoPL getDatosContacto() {
		return datosContacto;
	}

	public void setDatosContacto(DatosContactoPL datosContacto) {
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
		PersonaPL other = (PersonaPL) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", direccion=" + direccion + ", datosContacto=" + datosContacto + "]";
	}

}
