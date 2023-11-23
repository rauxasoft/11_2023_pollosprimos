package com.sinensia.pollosprimos.backend.business.model.dtos;

import java.io.Serializable;

import com.sinensia.pollosprimos.backend.business.model.Categoria;

public class EstadisticaDTO2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Categoria categoria;
	private int cantidad;
	
	public EstadisticaDTO2(Categoria categoria, int cantidad) {
		this.categoria = categoria;
		this.cantidad = cantidad;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getCantidad() {
		return cantidad;
	}

	@Override
	public String toString() {
		return "EstadisticaDTO2 [categoria=" + categoria + ", cantidad=" + cantidad + "]";
	}
	
}
