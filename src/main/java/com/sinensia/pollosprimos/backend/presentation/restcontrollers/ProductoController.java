package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;
import com.sinensia.pollosprimos.backend.common.Pagina;
import com.sinensia.pollosprimos.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	private ProductoServices productoServices;
	
	@Autowired
	public ProductoController(ProductoServices productoServices) {
		this.productoServices = productoServices;
	}	
	
	@GetMapping("/{codigo}")
	public Producto read(@PathVariable Long codigo) {
		
		Optional<Producto> optional = productoServices.read(codigo);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el producto " + codigo, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	
	@GetMapping
	public List<Producto> getAll(@RequestParam(required = false) Double min, 
								 @RequestParam(required = false) Double max){
		
		List<Producto> productos = null;
		
		if(min != null && max != null) {
			productos = productoServices.getBetweenPriceRange(min, max);
		} else {
			productos = productoServices.getAll();
		}
		
		return productos;
	}
	
	@GetMapping("/pagina")
	public Pagina<Producto> getPagina(@RequestParam("page-number") Integer pageNumber,
									  @RequestParam("page-size") Integer pageSize){

		return productoServices.getPagina(pageNumber, pageSize);
	}
	
}
