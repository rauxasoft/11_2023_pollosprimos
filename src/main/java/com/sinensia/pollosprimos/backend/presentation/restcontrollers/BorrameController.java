package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosprimos.backend.integration.model.CategoriaPL;
import com.sinensia.pollosprimos.backend.integration.repositories.CategoriaPLPagingRepostiry;

@RestController
public class BorrameController {
	
	@Autowired
	private CategoriaPLPagingRepostiry categoriaPLPagingRepostiry;
	
	// htto://localhost:8080/categorias/pagina?page-number=0&page-size=5
	
	@GetMapping("/categorias-borrame/pagina")
	public Page<CategoriaPL> getPagina(@RequestParam("page-number") Integer pageNumber,
			                           @RequestParam("page-size") Integer pageSize) {
		
		Page<CategoriaPL> pagina = categoriaPLPagingRepostiry.findAll(PageRequest.of(pageNumber, pageSize));

		return pagina;
	}

}
