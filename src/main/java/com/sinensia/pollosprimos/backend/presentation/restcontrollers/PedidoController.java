package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.business.services.PedidoServices;
import com.sinensia.pollosprimos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping
	public List<Pedido> getAll(){
		return pedidoServices.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pedido pedido, UriComponentsBuilder ucb) {
		
		System.out.println(pedido);
		
		Long numero = null;
		
		try {
			numero = pedidoServices.create(pedido);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);	
		}
				
		return ResponseEntity.created(ucb.path("/pedido/{numero}").build(numero))
								.build();
	}
	
}
