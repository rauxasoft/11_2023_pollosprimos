package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
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
class CamareroControllerTest {
	
	@Autowired
	private MockMvc miniPostman;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CamareroServices camareroServices;
	
	private Camarero camarero1;
	private Camarero camarero2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void creamos_camarero_ok() throws Exception {
		
		camarero1.setId(null);
		
		when(camareroServices.create(camarero1)).thenReturn(1000L);
		
		String requestBody = objectMapper.writeValueAsString(camarero1);
		
		miniPostman.perform(post("/camareros").content(requestBody).contentType("application/json"))
		                  .andExpect(status().isCreated())
		                  .andExpect(header().string("Location","http://localhost/camareros/1000"));
		
	}
	
	@Test
	void creamos_camarero_con_id_NO_null() throws Exception {
		
		when(camareroServices.create(camarero1)).thenThrow(new IllegalStateException("EL MENSAJE"));
		
		String requestBody = objectMapper.writeValueAsString(camarero1);
		
		MvcResult respuesta = miniPostman.perform(post("/camareros").content(requestBody).contentType("application/json"))
								.andExpect(status().isBadRequest())
								.andReturn();
		
		RespuestaError respuestaError = new RespuestaError("EL MENSAJE");
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String responseBodyEsperada = objectMapper.writeValueAsString(respuestaError);
				
		assertThat(responseBody).isEqualToIgnoringWhitespace(responseBodyEsperada);
		
	}
	
	@Test
	void solicitamos_todos_los_camareros() throws Exception {
	
		List<Camarero> camareros = Arrays.asList(camarero1, camarero2);
		
		when(camareroServices.getAll()).thenReturn(camareros);
		
		MvcResult respuesta = miniPostman.perform(get("/camareros"))
								.andExpect(status().isOk())
								.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String responseBodyEsperada = objectMapper.writeValueAsString(camareros);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(responseBodyEsperada);
	}
	
	@Test
	void solicitamos_camarero_EXISTENTE_a_partir_de_su_id() throws Exception {
		
		when(camareroServices.read(100L)).thenReturn(Optional.of(camarero1));
		
		MvcResult respuesta = miniPostman.perform(get("/camareros/100"))
				.andExpect(status().isOk())
				.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String responseBodyEsperada = objectMapper.writeValueAsString(camarero1);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(responseBodyEsperada);
	}
	
	@Test
	void solicitamos_camarero_NO_EXISTENTE_a_partir_de_su_id() throws Exception {
		
		when(camareroServices.read(100L)).thenReturn(Optional.empty());
		
		MvcResult respuesta = miniPostman.perform(get("/camareros/100"))
				.andExpect(status().isNotFound())
				.andReturn();
		
		RespuestaError respuestaError = new RespuestaError("No se encuentra el camarero con id 100");
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String responseBodyEsperada = objectMapper.writeValueAsString(respuestaError);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(responseBodyEsperada);
		
	}
	
	@Test
	void actualizamos_camarero_ok() throws Exception {
		
		String requestBody = objectMapper.writeValueAsString(camarero1);
		
		miniPostman.perform(put("/camareros/100").content(requestBody).contentType("application/json"))
						.andExpect(status().isNoContent());
		
		verify(camareroServices, times(1)).update(camarero1);
		
	}
	
	@Test
	void actualizamos_camarero_NO_existe() throws Exception {
		
		Camarero camareroSinId = new Camarero();
		
		Camarero camareroConId = new Camarero();
		camareroConId.setId(100L);
		
		String requestBody = objectMapper.writeValueAsString(camareroSinId);
		
		doThrow(new IllegalStateException("mensaje de que no existe un camarero con id 100")).when(camareroServices).update(camareroConId);
		
		MvcResult respuesta = miniPostman.perform(put("/camareros/100").content(requestBody).contentType("application/json"))
								.andExpect(status().isNotFound())
								.andReturn();
		
		RespuestaError respuestaError = new RespuestaError("mensaje de que no existe un camarero con id 100");
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String responseBodyEsperada = objectMapper.writeValueAsString(respuestaError);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(responseBodyEsperada);
		
	}
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private void initObjects() {
		
		camarero1 = new Camarero();
		camarero2 = new Camarero();
		
		camarero1.setId(100L);
		camarero2.setId(101L);
		
	}
	
}
