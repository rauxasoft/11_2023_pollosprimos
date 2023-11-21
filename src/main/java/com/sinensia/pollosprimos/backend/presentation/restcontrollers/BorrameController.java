package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.integration.model.CamareroPL;
import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.model.ClientePL;
import com.sinensia.pollosprimos.backend.integration.model.EstablecimientoPL;
import com.sinensia.pollosprimos.backend.integration.model.PedidoPL;
import com.sinensia.pollosprimos.backend.integration.model.ProductoPL;
import com.sinensia.pollosprimos.backend.integration.repositories.CamareroPLRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.CategoriaPLRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.ClientePLRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.EstablecimientoPLRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.PedidoPLRepository;
import com.sinensia.pollosprimos.backend.integration.repositories.ProductoPLRepository;

@RestController
@RequestMapping("/pruebas")
public class BorrameController {

	@Autowired
	private CategoriaPLRepository categoriaPLRepository;
	
	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Autowired
	private EstablecimientoPLRepository establecimientoPLRepository;
	
	@Autowired
	private ClientePLRepository clientePLRepository;
	
	@Autowired
	private CamareroPLRepository camareroPLRepository;
	
	@Autowired
	private PedidoPLRepository pedidoPLRepository;
	
	@GetMapping("/pedidos")
	public List<PedidoPL> getPedidos(){
		return pedidoPLRepository.findAll();
	}
	
	@GetMapping("/camareros")
	public List<CamareroPL> getCamareros(){
		return camareroPLRepository.findAll();
	}
	
	@GetMapping("/clientes")
	public List<ClientePL> getClientes(){
		return clientePLRepository.findAll();
	}
	
	@GetMapping("/establecimientos")
	public List<EstablecimientoPL> getEstablecimientos(){
		return establecimientoPLRepository.findAll();
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
