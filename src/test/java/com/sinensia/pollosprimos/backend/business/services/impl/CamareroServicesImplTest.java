package com.sinensia.pollosprimos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.integration.model.CamareroPL;
import com.sinensia.pollosprimos.backend.integration.repositories.CamareroPLRepository;

@ExtendWith(MockitoExtension.class)
public class CamareroServicesImplTest {

	@Mock
	private CamareroPLRepository camareroPLRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@InjectMocks
	private CamareroServicesImpl camareroServicesImpl;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void creamos_camarero_ok() {
		
		Camarero camarero = new Camarero();
		camarero.setId(null);
		camarero.setDni("11111111R");
		
		CamareroPL camareroPL = new CamareroPL();
		CamareroPL createdCamareroPL = new CamareroPL();
		createdCamareroPL.setId(666L);
		
		when(camareroPLRepository.existsByDni("11111111R")).thenReturn(false);
		when(mapper.map(camarero, CamareroPL.class)).thenReturn(camareroPL);
		when(camareroPLRepository.save(camareroPL)).thenReturn(createdCamareroPL);
		
		Long id = camareroServicesImpl.create(camarero);
		
		assertEquals(666, id);
		
	}
	
	@Test
	void creamos_camarero_con_id_NO_null() {
		
		Camarero camarero = new Camarero();
		camarero.setId(500L);
		
		Exception exception = assertThrows(IllegalStateException.class, ()->{
			camareroServicesImpl.create(camarero);
		});
		
		assertEquals("Para crear un camarero el id ha de ser null", exception.getMessage());
	}
	
	@Test
	void creamos_camarero_con_dni_NULL() {
		
		Camarero camarero = new Camarero();
		camarero.setId(null);
		camarero.setDni(null);
		
		Exception exception = assertThrows(IllegalStateException.class, ()->{
			camareroServicesImpl.create(camarero);
		});
		
		assertEquals("No se puede crear un camarero con DNI null", exception.getMessage());
		
	}
	
	@Test
	void creamos_camarero_con_dni_EXISTENTE() {
		
		Camarero camarero = new Camarero();
		camarero.setId(null);
		camarero.setDni("11111111R");
		
		when(camareroPLRepository.existsByDni("11111111R")).thenReturn(true);
		
		Exception exception = assertThrows(IllegalStateException.class, ()->{
			camareroServicesImpl.create(camarero);
		});
		
		assertEquals("No se puede crear el camarero. Ya existe un camarero con DNI 11111111R", exception.getMessage());
			
	}
	
	@Test
	void read_camarero_existente() {
		
		CamareroPL camareroPL = new CamareroPL();
		camareroPL.setId(100L);
		
		Camarero camarero = new Camarero();
		camarero.setId(100L);
		
		when(camareroPLRepository.findById(100L)).thenReturn(Optional.of(camareroPL));
		when(mapper.map(camareroPL, Camarero.class)).thenReturn(camarero);
		
		Optional<Camarero> optional = camareroServicesImpl.read(100L);
		
		assertTrue(optional.isPresent());
		assertEquals(camarero, optional.get());
	}
	
	@Test
	void read_camarero_NO_existente() {
		
		when(camareroPLRepository.findById(100L)).thenReturn(Optional.empty());
		
		Optional<Camarero> optional = camareroServicesImpl.read(100L);
		
		assertTrue(optional.isEmpty());
	}
	
	@Test
	void read_camarero_by_dni_existente() {
		
		CamareroPL camareroPL = new CamareroPL();
		Camarero camarero = new Camarero();
		camarero.setId(50L);
		
		when(camareroPLRepository.findByDni("11111111R")).thenReturn(camareroPL);
		when(mapper.map(camareroPL, Camarero.class)).thenReturn(camarero);
		
		Optional<Camarero> optional = camareroServicesImpl.read("11111111R");
		
		assertTrue(optional.isPresent());
		assertEquals(camarero, optional.get());
	}
	
	@Test
	void read_camarero_by_dni_NO_existente() {
		
		when(camareroPLRepository.findByDni("11111111R")).thenReturn(null);
		
		Optional<Camarero> optional = camareroServicesImpl.read("11111111R");
		
		assertTrue(optional.isEmpty());
	}
	
	@Test
	void eliminamos_camarero_ok() {
		
		when(camareroPLRepository.existsById(45L)).thenReturn(true);
		
		camareroServicesImpl.delete(45L);
		
		verify(camareroPLRepository, times(1)).deleteById(45L);
	}
	
	@Test
	void eliminamos_camarero_inexistente() {
		
		when(camareroPLRepository.existsById(45L)).thenReturn(false);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			camareroServicesImpl.delete(45L);
		});
		
		assertEquals("El camarero con id 45 no existe. No se puede eliminar", exception.getMessage());
	}
	
	@Test
	void obtenemos_numero_total_camareros() {
		
		when(camareroPLRepository.count()).thenReturn(23L);
		
		int numeroTotalCamareros = camareroServicesImpl.getNumeroTotalCamareros();
		
		assertEquals(23, numeroTotalCamareros);
	}
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private void initObjects() {
		
	}
	
}
