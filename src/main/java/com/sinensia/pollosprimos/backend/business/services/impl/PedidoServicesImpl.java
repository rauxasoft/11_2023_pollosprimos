package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Cliente;
import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.business.services.PedidoServices;
import com.sinensia.pollosprimos.backend.integration.model.ClientePL;
import com.sinensia.pollosprimos.backend.integration.model.PedidoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.PedidoPLRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoServicesImpl implements PedidoServices {

	@Autowired
	private PedidoPLRepository pedidoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public Long create(Pedido pedido) {
		
		if(pedido.getNumero() != null) {
			throw new IllegalStateException("No se puede crear el pedido. El número ha de ser null");
		}
		
		PedidoPL pedidoPL = mapper.map(pedido, PedidoPL.class);
		
		PedidoPL createdPedidoPL = pedidoPLRepository.save(pedidoPL);
		
		return createdPedidoPL.getNumero();
	}

	@Override
	public Optional<Pedido> read(Long numero) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Pedido> getAll() {
		
		List<PedidoPL> pedidosPL = pedidoPLRepository.findAll(Sort.by("numero").descending());
		
		return pedidosPL.stream().map(x -> mapper.map(x, Pedido.class)).collect(Collectors.toList());
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

	@Override
	@Transactional
	public void update(Long numeroPedido, Map<String, Object> mapaAtributos) {
		
		Optional<PedidoPL> optional = pedidoPLRepository.findById(numeroPedido);
		
		if(optional.isEmpty()) {
			throw new IllegalStateException("No existe el pedido " + numeroPedido);
		}
		
		PedidoPL pedidoPL = optional.get();
			
		Set<String> nombresAtributos = mapaAtributos.keySet();
		
		for(String nombreAtributo: nombresAtributos) {
			
			switch(nombreAtributo) {
				case "comentario" : pedidoPL.setComentario((String) mapaAtributos.get(nombreAtributo)); break;
				case "cliente" : {
					Cliente cliente = (Cliente) mapaAtributos.get(nombreAtributo);
					ClientePL clientePL = mapper.map(cliente, ClientePL.class);
					pedidoPL.setCliente(clientePL);
					break;
				}
				case "fecha" : pedidoPL.setFecha((Date) mapaAtributos.get(nombreAtributo)); break;
			}
			
		}
		
	}

	@Override
	@Transactional
	public void update(Pedido pedido) {
		
		if(pedido.getNumero() == null) {
			throw new IllegalStateException("No se puede actualizar un pedido con numero null");
		}
		
		boolean existe = pedidoPLRepository.existsById(pedido.getNumero());
		
		if(!existe) {
			throw new IllegalStateException("No existe el pedido " + pedido.getNumero());
		}
		
		PedidoPL pedidoPL = mapper.map(pedido, PedidoPL.class);
		
		pedidoPLRepository.save(pedidoPL);
		
	}
	
}
