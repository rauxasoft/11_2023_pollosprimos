package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.business.services.PedidoServices;

@Service
public class PedidoServicesImpl implements PedidoServices {

	@Override
	public Long create(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pedido> read(Long numero) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Pedido> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void procesar(Long numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entregar(Long numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void servir(Long numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelar(Long numero) {
		// TODO Auto-generated method stub
		
	}

}
