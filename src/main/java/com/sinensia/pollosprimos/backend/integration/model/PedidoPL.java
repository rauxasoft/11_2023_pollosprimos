package com.sinensia.pollosprimos.backend.integration.model;

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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="PEDIDOS")
public class PedidoPL implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CODIGO")
	@GeneratedValue(generator="PEDIDO_SEQ")
	private Long numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_HORA")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_CLIENTE")
	private ClientePL cliente;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_CAMARERO")
	private CamareroPL camarero;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_ESTABLECIMIENTO")
	private EstablecimientoPL establecimiento;
	
	@Enumerated(EnumType.STRING)
	private EstadoPedidoPL estado;
	
	private String comentario;
	
	@ElementCollection
	@CollectionTable(name="LINEAS_PEDIDO", joinColumns=@JoinColumn(name="CODIGO_PEDIDO"))
	private List<LineaPedidoPL> lineas;
	
	public PedidoPL() {
		
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

	public ClientePL getCliente() {
		return cliente;
	}

	public void setCliente(ClientePL cliente) {
		this.cliente = cliente;
	}

	public CamareroPL getCamarero() {
		return camarero;
	}

	public void setCamarero(CamareroPL camarero) {
		this.camarero = camarero;
	}

	public EstablecimientoPL getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(EstablecimientoPL establecimiento) {
		this.establecimiento = establecimiento;
	}

	public EstadoPedidoPL getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedidoPL estado) {
		this.estado = estado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<LineaPedidoPL> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedidoPL> lineas) {
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
		PedidoPL other = (PedidoPL) obj;
		return Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "Pedido [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", camarero=" + camarero
				+ ", establecimiento=" + establecimiento + ", estado=" + estado + ", comentario=" + comentario
				+ ", lineas=" + lineas + "]";
	}
	
}
