package com.sinensia.pollosprimos.backend.business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="PEDIDOS")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CODIGO")
	private Long numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_HORA")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_CLIENTE")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_CAMARERO")
	private Camarero camarero;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_ESTABLECIMIENTO")
	private Establecimiento establecimiento;
	
	@Enumerated(EnumType.STRING)
	private EstadoPedido estado;
	
	private String comentario;
	
	@ElementCollection
	@CollectionTable(name="LINEAS_PEDIDO", joinColumns=@JoinColumn(name="CODIGO_PEDIDO"))
	private List<LineaPedido> lineas;
	
	public Pedido() {
		
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Camarero getCamarero() {
		return camarero;
	}

	public void setCamarero(Camarero camarero) {
		this.camarero = camarero;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
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
		Pedido other = (Pedido) obj;
		return Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "Pedido [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", camarero=" + camarero
				+ ", establecimiento=" + establecimiento + ", estado=" + estado + ", comentario=" + comentario
				+ ", lineas=" + lineas + "]";
	}
	
}
