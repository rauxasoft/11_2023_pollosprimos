package com.sinensia.pollosprimos.backend.presentation.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;

@Controller
@RequestMapping("/app")
public class AppProductoController {
	
	private ProductoServices productoServices;
	private CategoriaServices categoriaServices;

	@Autowired
	public AppProductoController(ProductoServices productoServices, CategoriaServices categoriaServices) {
		this.productoServices = productoServices;
		this.categoriaServices = categoriaServices;
	}

	@GetMapping("/productos")
	public ModelAndView getPaginaProductos(ModelAndView mav) {
		
		mav.addObject("productos", productoServices.getAll());
		mav.setViewName("listado-productos");
		
		return mav;
	}
	
	@GetMapping("/ficha-producto")
	public ModelAndView getFichaProducto(ModelAndView mav, 
										 @RequestParam(required = false) Long codigo) {
		
		String modo = null;
		
		if(codigo != null) {
			modo = "editar";
			Optional<Producto> optional = productoServices.read(codigo);
			Producto producto = optional.orElse(null);
			mav.addObject("producto", producto);
		} else {
			modo = "alta";
			Producto producto = new Producto();
			producto.setFechaAlta(new Date());
			mav.addObject("producto", producto);
		}
		
		mav.addObject("modo", modo);
		mav.addObject("categorias", categoriaServices.getAll());
		mav.setViewName("ficha-producto");
		return mav;
	}
	
	@PostMapping("procesar-formulario-producto")
	public RedirectView procesarFormulario(@ModelAttribute Producto producto, ModelAndView mav, BindingResult result) {
		
		Long codigo = producto.getCodigo();
			
		if(codigo != null) {
			productoServices.update(producto);
		} else {
			productoServices.create(producto);
		}
		
		if(result.hasErrors()) {
			
		}
		
		return new RedirectView("productos");
	}
	
}
