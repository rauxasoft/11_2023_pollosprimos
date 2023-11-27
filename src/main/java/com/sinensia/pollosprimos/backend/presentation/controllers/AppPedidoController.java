package com.sinensia.pollosprimos.backend.presentation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.business.services.PedidoServices;

@Controller
@RequestMapping("/app")
public class AppPedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping("/pedidos")
	public ModelAndView getListadoPedidos(ModelAndView mav, @RequestParam(required = false) Long numero) {
		
		if(numero == null) {
			mav.addObject("pedidos", pedidoServices.getAll());
			mav.setViewName("listado-pedidos");
		} else {
			Optional<Pedido> optional = pedidoServices.read(numero);
			Pedido pedido = optional.get();
			mav.addObject("pedido", pedido);
			mav.setViewName("ficha-pedido");
		}
		
		return mav;
	}
}
