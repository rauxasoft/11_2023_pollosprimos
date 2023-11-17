package com.sinensia.pollosprimos.backend.presentation.restcontrollers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.services.CamareroServices;

@WebMvcTest(value=CamareroController.class)
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

}
