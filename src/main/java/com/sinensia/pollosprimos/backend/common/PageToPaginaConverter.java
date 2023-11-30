package com.sinensia.pollosprimos.backend.common;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageToPaginaConverter<T> {
		
	public Pagina<T> convert(Page<?> page, List<T> elementos){
			
		Pagina<T> pagina = new Pagina<>();
			
		pagina.setElementos(elementos);
		pagina.setNumeroTotalElementos(page.getTotalElements());
		pagina.setNumeroPagina(page.getNumber());
		pagina.setNumeroTotalPaginas(page.getTotalPages());
		pagina.setNumeroElementos(page.getSize());
		pagina.setPrimeraPagina(page.isFirst());
		pagina.setUltimaPagina(page.isLast());
		
		return pagina;
	}
}
