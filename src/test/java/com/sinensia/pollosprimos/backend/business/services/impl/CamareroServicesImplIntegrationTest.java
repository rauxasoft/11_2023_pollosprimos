package com.sinensia.pollosprimos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sinensia.pollosprimos.backend.business.model.Camarero;

@SpringBootTest
public class CamareroServicesImplIntegrationTest {

	@Autowired
	private CamareroServicesImpl camareroServicesImpl;
	
	@Test
	void read_camarero_existe() {
		
		Optional<Camarero> optional = camareroServicesImpl.read(11L);
		
		assertTrue(optional.isPresent());
		assertEquals(11L, optional.get().getId());
	}
}
