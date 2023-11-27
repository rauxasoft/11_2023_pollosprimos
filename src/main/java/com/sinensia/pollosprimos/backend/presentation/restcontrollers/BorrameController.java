package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.model.Cliente;
import com.sinensia.pollosprimos.backend.business.services.PedidoServices;

@RestController
public class BorrameController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping("/actualizar1")
	public Object actualizarComentario_Cliente() {
		
		Cliente cliente = new Cliente();
		cliente.setId(10L);
		
		Map<String, Object> mapaAtributos = new HashMap<>();
		
		mapaAtributos.put("comentario", "No le pongan sal!");
		mapaAtributos.put("cliente", cliente);
		
		pedidoServices.update(1014L, mapaAtributos);
		
		return "Pedido actualizado...";
	}
	
	@GetMapping("/actualizar2")
	public Object actualizarComentario() {
		
		
		Map<String, Object> mapaAtributos = new HashMap<>();
		
		mapaAtributos.put("comentario", "No le pongan sal! ES LO UNICO QUE ESTAMOS ACTUALIZANDO");
		
		pedidoServices.update(1014L, mapaAtributos);
		
		return "Pedido actualizado...";
	}
	
	@GetMapping("/actualizar3")
	public Object actualizarFecha() {
		
		Map<String, Object> mapaAtributos = new HashMap<>();
		
		mapaAtributos.put("fecha", new Date(10000000L));
		
		pedidoServices.update(1014L, mapaAtributos);
		
		return "Pedido actualizado...";
	}
}
