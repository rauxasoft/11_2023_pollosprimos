package com.sinensia.pollosprimos.backend.presentation.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
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

import com.sinensia.pollosprimos.backend.business.model.Categoria;
import com.sinensia.pollosprimos.backend.business.model.Producto;
import com.sinensia.pollosprimos.backend.business.services.CategoriaServices;
import com.sinensia.pollosprimos.backend.business.services.ProductoServices;
import com.sinensia.pollosprimos.backend.presentation.model.ProductoVO;

@Controller
@RequestMapping("/app")
public class AppProductoController {
	
	private ProductoServices productoServices;
	private CategoriaServices categoriaServices;
	private DozerBeanMapper mapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public AppProductoController(ProductoServices productoServices, 
			                     CategoriaServices categoriaServices,
			                     DozerBeanMapper mapper) {
		
		this.productoServices = productoServices;
		this.categoriaServices = categoriaServices;
		this.mapper = mapper;
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
		
		List<Categoria> categorias = categoriaServices.getAll();
		
		String modo = null;
		
		if(codigo != null) {
			modo = "editar";
			Optional<Producto> optional = productoServices.read(codigo);
			Producto producto = optional.orElse(null);
			ProductoVO productoVO = mapper.map(producto, ProductoVO.class);
			mav.addObject("producto", productoVO);
		} else {
			modo = "alta";
			ProductoVO productoVO = new ProductoVO();
			productoVO.setFechaAlta(sdf.format(new Date()));
			mav.addObject("producto", productoVO);
		}
		
		mav.addObject("modo", modo);
		mav.addObject("categorias", categorias);
		mav.setViewName("ficha-producto");
		return mav;
	}
	
	@PostMapping("procesar-formulario-producto")
	public RedirectView procesarFormulario(@ModelAttribute ProductoVO productoVO, ModelAndView mav, BindingResult result) {
		
		Long codigo = productoVO.getCodigo();
			
		if(codigo != null) {
			Producto producto = mapper.map(productoVO, Producto.class);
			productoServices.update(producto);
		} else {
			Producto producto = mapper.map(productoVO, Producto.class);
			productoServices.create(producto);
		}
		
		if(result.hasErrors()) {
			System.out.println(result);
		}
		
		return new RedirectView("productos");
	}
	
}
