package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Cliente;
import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.business.services.PedidoServices;
import com.sinensia.pollosprimos.backend.common.PageToPaginaConverter;
import com.sinensia.pollosprimos.backend.common.Pagina;
import com.sinensia.pollosprimos.backend.integration.model.ClientePL;
import com.sinensia.pollosprimos.backend.integration.model.EstadoPedidoPL;
import com.sinensia.pollosprimos.backend.integration.model.PedidoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.PedidoPLPagingRepostiry;
import com.sinensia.pollosprimos.backend.integration.repositories.PedidoPLRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoServicesImpl implements PedidoServices {

	private PedidoPLRepository pedidoPLRepository;
	private PedidoPLPagingRepostiry pedidoPLPagingRepository;
	private DozerBeanMapper mapper;
	
	@Autowired
	public PedidoServicesImpl(PedidoPLRepository pedidoPLRepository, 
							  PedidoPLPagingRepostiry pedidoPLPagingRepository, 
							  DozerBeanMapper mapper ) {
		this.pedidoPLRepository = pedidoPLRepository;
		this.pedidoPLPagingRepository = pedidoPLPagingRepository;
		this.mapper = mapper;
	}
	
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
	
		Optional<PedidoPL> optional = pedidoPLRepository.findById(numero);
		
		Pedido pedido = null;
		
		if(optional.isPresent()) {
			pedido = mapper.map(optional.get(), Pedido.class);
		}
	
		return Optional.ofNullable(pedido);
	}

	@Override
	public List<Pedido> getAll() {
		
		List<PedidoPL> pedidosPL = pedidoPLRepository.findAll(Sort.by("numero").descending());
		
		return pedidosPL.stream()
				.map(x -> mapper.map(x, Pedido.class))
				.toList();
	}

	@Override
	@Transactional
	public void procesar(Long numero) {
		
		PedidoPL pedidoPL = obtenerPedidoPL(numero);
		EstadoPedidoPL estado = pedidoPL.getEstado();
		
		if(estado != EstadoPedidoPL.NUEVO) {
			throw new IllegalStateException("No se puede marcar como EN_PROCESO un pedido en estado " + estado);
		}
			
		pedidoPL.setEstado(EstadoPedidoPL.EN_PROCESO);	
		
	}

	@Override
	@Transactional
	public void entregar(Long numero) {
		
		PedidoPL pedidoPL = obtenerPedidoPL(numero);
		EstadoPedidoPL estado = pedidoPL.getEstado();
		
		if(estado != EstadoPedidoPL.EN_PROCESO) {
			throw new IllegalStateException("No se puede marcar como PENDIENTE_ENTREGA un pedido en estado " + estado);
		}
			
		pedidoPL.setEstado(EstadoPedidoPL.PENDIENTE_ENTREGA);	
		
	} 	

	@Override
	@Transactional
	public void servir(Long numero) {
		
		PedidoPL pedidoPL = obtenerPedidoPL(numero);
		EstadoPedidoPL estado = pedidoPL.getEstado();
		
		if(estado != EstadoPedidoPL.PENDIENTE_ENTREGA) {
			throw new IllegalStateException("No se puede marcar como SERVIDO un pedido en estado " + estado);
		}
			
		pedidoPL.setEstado(EstadoPedidoPL.SERVIDO);	
		
	}

	@Override
	@Transactional
	public void cancelar(Long numero) {
		
		PedidoPL pedidoPL = obtenerPedidoPL(numero);
		EstadoPedidoPL estado = pedidoPL.getEstado();
		
		if(estado == EstadoPedidoPL.CANCELADO || estado == EstadoPedidoPL.SERVIDO) {
			throw new IllegalStateException("No se puede cancelar un pedido en estado " + estado);
		}
			
		pedidoPL.setEstado(EstadoPedidoPL.CANCELADO);	

	}

	@Override
	@Transactional
	public void update(Long numeroPedido, Map<String, Object> mapaAtributos) {
		
		PedidoPL pedidoPL = obtenerPedidoPL(numeroPedido);
			
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
				
				default: throw new IllegalArgumentException("El atributo " + nombreAtributo + " no existe o no se puede actualizar");
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
	
	@Override
	public Pagina<Pedido> getPagina(int pageNumber, int pageSize) {
		
		Page<PedidoPL> page = pedidoPLPagingRepository.findAll(PageRequest.of(pageNumber, pageSize));
		
		PageToPaginaConverter<Pedido> conversor = new PageToPaginaConverter<>();
		
		List<Pedido> pedidos = page.getContent().stream()
										.map(x -> mapper.map(x, Pedido.class))
										.toList();
		
		return conversor.convert(page, pedidos);
	}
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private PedidoPL obtenerPedidoPL(Long numeroPedido) {
		
		Optional<PedidoPL> optional = pedidoPLRepository.findById(numeroPedido);
		
		if(optional.isEmpty()) {
			throw new IllegalStateException("No existe el pedido " + numeroPedido);
		}
		
		return optional.get();
	}

}
