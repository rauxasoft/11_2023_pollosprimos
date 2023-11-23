package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.services.ProductoServices;
import com.sinensia.pollosprimos.backend.integration.repositories.ProductoPLRepository;

@RestController
public class BorrameController {

	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/prueba-repo")
	private List<Object[]> gesDatosFromRepository(){
		return productoPLRepository.getEstadisticaNumeroProductos();
	}
	
	@GetMapping("/prueba-servicio1")
	private Object getDatosFromService1(){
		return productoServices.getEstadisticaNumeroProductoPorCategoria();
	}
	
	@GetMapping("/prueba-servicio2")
	private Object getDatosFromService2(){
		return productoServices.getEstadisticasDTO1();
	}
	
	@GetMapping("/prueba-servicio3")
	private Object getDatosFromService3(){
		return productoServices.getEstadisticasDTO2();
	}

}
