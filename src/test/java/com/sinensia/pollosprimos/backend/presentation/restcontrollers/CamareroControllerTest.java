package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.pollosprimos.backend.auditoria.filtro.FiltroAuditor;
import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.services.CamareroServices;
import com.sinensia.pollosprimos.backend.presentation.config.RespuestaError;

@WebMvcTest(value=CamareroController.class, 
            excludeFilters=@ComponentScan.Filter(classes=FiltroAuditor.class, type=FilterType.ASSIGNABLE_TYPE))
public class CamareroControllerTest {
	
	@Autowired
	private MockMvc miniPostman;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CamareroServices camareroServices;
	
	@Test
	void creamos_camarero_ok() throws Exception {
		
		Camarero nuevoCamarero = new Camarero();
		nuevoCamarero.setNombre("Froilán");
		
		when(camareroServices.create(nuevoCamarero)).thenReturn(1000L);
		
		String requestBody = objectMapper.writeValueAsString(nuevoCamarero);
		
		miniPostman.perform(post("/camareros").content(requestBody).contentType("application/json"))
		                  .andExpect(status().isCreated())
		                  .andExpect(header().string("Location","http://localhost/camareros/1000"));
		
	}
	
	@Test
	void creamos_camarero_con_id_NO_null() throws Exception {
		
		Camarero camarero = new Camarero();
		camarero.setId(100L);
		
		when(camareroServices.create(camarero)).thenThrow(new IllegalStateException("EL MENSAJE"));
		
		String requestBody = objectMapper.writeValueAsString(camarero);
		
		MvcResult respuesta = miniPostman.perform(post("/camareros").content(requestBody).contentType("application/json"))
								.andExpect(status().isBadRequest())
								.andReturn();
		
		RespuestaError respuestaError = new RespuestaError("EL MENSAJE");
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String responseBodyEsperada = objectMapper.writeValueAsString(respuestaError);
				
		assertThat(responseBody).isEqualToIgnoringWhitespace(responseBodyEsperada);
		
	}
	
}
