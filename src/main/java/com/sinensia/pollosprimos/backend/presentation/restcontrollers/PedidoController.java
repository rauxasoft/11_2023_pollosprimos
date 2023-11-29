package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.business.services.PedidoServices;
import com.sinensia.pollosprimos.backend.common.Pagina;
import com.sinensia.pollosprimos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private PedidoServices pedidoServices;

	@Autowired
	public PedidoController(PedidoServices pedidoServices) {
		this.pedidoServices = pedidoServices;
	}
	
	@GetMapping
	public List<Pedido> getAll(){
		return pedidoServices.getAll();
	}
	
	@GetMapping("/{numero}")
	public Pedido read(@PathVariable Long numero) {
		
		Optional<Pedido> optional = pedidoServices.read(numero);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el pedido " + numero, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Pedido pedido, UriComponentsBuilder ucb) {
				
		Long numero = null;
		
		try {
			numero = pedidoServices.create(pedido);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);	
		}
				
		return ResponseEntity.created(ucb.path("/pedido/{numero}").build(numero))
								.build();
	}
	
	@GetMapping("/pagina")
	public Pagina<Pedido> getPagina(@RequestParam("page-number") Integer pageNumber,
									   @RequestParam("page-size") Integer pageSize){
		// TODO
		return null;
	}
	
}
