package com.sinensia.pollosprimos.backend.integration.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class LineaPedidoPL implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="CODIGO_PRODUCTO")
	private ProductoPL producto;
	
	private int cantidad;      
	private double precio;
	
	public LineaPedidoPL() {
		// No args constructor
	}

	public ProductoPL getProducto() {
		return producto;
	}

	public void setProducto(ProductoPL producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "LineaPedido [producto=" + producto + ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}
	
}
