package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.model.Cliente;
import com.sinensia.pollosprimos.backend.business.model.Establecimiento;
import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.model.ProductoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.CamareroRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.CategoriaPLRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.ClienteRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.EstablecimientoRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.PedidoRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.ProductoPLRepository;

@RestController
@RequestMapping("/pruebas")
public class BorrameController {

	@Autowired
	private CategoriaPLRepository categoriaPLRepository;
	
	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Autowired
	private EstablecimientoRepository establecimientoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CamareroRepository camareroRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/pedidos")
	public List<Pedido> getPedidos(){
		return pedidoRepository.findAll();
	}
	
	@GetMapping("/camareros")
	public List<Camarero> getCamareros(){
		return camareroRepository.findAll();
	}
	
	@GetMapping("/clientes")
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/establecimientos")
	public List<Establecimiento> getEstablecimientos(){
		return establecimientoRepository.findAll();
	}
	
	@GetMapping("/productos")
	public List<ProductoPL> getProductos(){
		return productoPLRepository.findAll();
	}
	
	@GetMapping("/categorias")
	public List<CategoriaPL> getCategorias(){
		return categoriaPLRepository.findAll();
	}
}
