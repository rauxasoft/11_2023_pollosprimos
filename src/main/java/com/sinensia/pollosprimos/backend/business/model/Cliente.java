package com.sinensia.pollosprimos.backend.business.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="CLIENTES")
@PrimaryKeyJoinColumn(name="CODIGO_CLIENTE")
public class Cliente extends Persona {
	private static final long serialVersionUID = 1L;
	
	@Column(name="GOLD")
	private boolean tarjetaGold;
	
	public Cliente() {
		
	}

	public boolean isTarjetaGold() {
		return tarjetaGold;
	}

	public void setTarjetaGold(boolean tarjetaGold) {
		this.tarjetaGold = tarjetaGold;
	}

	@Override
	public String toString() {
		return "Cliente [tarjetaGold=" + tarjetaGold + ", toString()=" + super.toString() + "]";
	}

}
