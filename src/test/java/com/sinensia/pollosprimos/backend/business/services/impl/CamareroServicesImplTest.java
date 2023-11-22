package com.sinensia.pollosprimos.backend.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
	
	private CamareroPL camareroPL1;
	private CamareroPL camareroPL2;
	
	private Camarero camarero1;
	private Camarero camarero2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void creamos_camarero_ok() {
		
		camarero1.setId(null);
		camarero1.setDni("11111111R");
		
		CamareroPL createdCamareroPL = new CamareroPL();
		createdCamareroPL.setId(666L);
		
		when(camareroPLRepository.existsByDni("11111111R")).thenReturn(false);
		when(mapper.map(camarero1, CamareroPL.class)).thenReturn(camareroPL1);
		when(camareroPLRepository.save(camareroPL1)).thenReturn(createdCamareroPL);
		
		Long id = camareroServicesImpl.create(camarero1);
		
		assertEquals(666, id);
		
	}
	
	@Test
	void creamos_camarero_con_id_NO_null() {
	
		Exception exception = assertThrows(IllegalStateException.class, ()->{
			camareroServicesImpl.create(camarero1);
		});
		
		assertEquals("Para crear un camarero el id ha de ser null", exception.getMessage());
	}
	
	@Test
	void creamos_camarero_con_dni_NULL() {
	
		camarero1.setId(null);
		camarero1.setDni(null);
		
		Exception exception = assertThrows(IllegalStateException.class, ()->{
			camareroServicesImpl.create(camarero1);
		});
		
		assertEquals("No se puede crear un camarero con DNI null", exception.getMessage());
		
	}
	
	@Test
	void creamos_camarero_con_dni_EXISTENTE() {
		
		camarero1.setId(null);
		camarero1.setDni("11111111R");
		
		when(camareroPLRepository.existsByDni("11111111R")).thenReturn(true);
		
		Exception exception = assertThrows(IllegalStateException.class, ()->{
			camareroServicesImpl.create(camarero1);
		});
		
		assertEquals("No se puede crear el camarero. Ya existe un camarero con DNI 11111111R", exception.getMessage());
			
	}
	
	@Test
	void read_camarero_existente() {
		
		when(camareroPLRepository.findById(1L)).thenReturn(Optional.of(camareroPL1));
		when(mapper.map(camareroPL1, Camarero.class)).thenReturn(camarero1);
		
		Optional<Camarero> optional = camareroServicesImpl.read(1L);
		
		assertTrue(optional.isPresent());
		assertEquals(camarero1, optional.get());
	}
	
	@Test
	void read_camarero_NO_existente() {
		
		when(camareroPLRepository.findById(100L)).thenReturn(Optional.empty());
		
		Optional<Camarero> optional = camareroServicesImpl.read(100L);
		
		assertTrue(optional.isEmpty());
	}
	
	@Test
	void read_camarero_by_dni_existente() {
		
		when(camareroPLRepository.findByDni("11111111R")).thenReturn(camareroPL1);
		when(mapper.map(camareroPL1, Camarero.class)).thenReturn(camarero1);
		
		Optional<Camarero> optional = camareroServicesImpl.read("11111111R");
		
		assertTrue(optional.isPresent());
		assertEquals(camarero1, optional.get());
	}
	
	@Test
	void read_camarero_by_dni_NO_existente() {
		
		when(camareroPLRepository.findByDni("11111111R")).thenReturn(null);
		
		Optional<Camarero> optional = camareroServicesImpl.read("11111111R");
		
		assertTrue(optional.isEmpty());
	}
	
	@Test
	void update_ok() {
		
		when(camareroPLRepository.existsById(1L)).thenReturn(true);
		when(mapper.map(camarero1, CamareroPL.class)).thenReturn(camareroPL1);
		
		camareroServicesImpl.update(camarero1);
		
		verify(camareroPLRepository, times(1)).save(camareroPL1);
	}
	
	@Test
	void update_con_id_null() {
		
		camarero1.setId(null);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			camareroServicesImpl.update(camarero1);
		});
		
		assertEquals("No se puede actualizar un camarero con id null", exception.getMessage());
		
	}
	
	@Test
	void update_camarero_NO_existente() {
		
		when(camareroPLRepository.existsById(1L)).thenReturn(false);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			camareroServicesImpl.update(camarero1);
		});
		
		assertEquals("El camarero 1 no existe. No se puede actualizar", exception.getMessage());
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
	void obtenemos_todos() {
		
		List<CamareroPL> camarerosPL = List.of(camareroPL1, camareroPL2);
		
		when(camareroPLRepository.findAll()).thenReturn(camarerosPL);
		when(mapper.map(camareroPL1, Camarero.class)).thenReturn(camarero1);
		when(mapper.map(camareroPL2, Camarero.class)).thenReturn(camarero2);
		
		List<Camarero> camareros = camareroServicesImpl.getAll();
		
		assertEquals(2, camareros.size());
		assertTrue(camareros.containsAll(List.of(camarero1, camarero2)));
	}
	
	@Test
	void obtenemos_by_nombre_like_ignore_case() {
		
		List<CamareroPL> camarerosPL = List.of(camareroPL1, camareroPL2);
		
		when(camareroPLRepository.findByNombreLikeIgnoreCase("orio")).thenReturn(camarerosPL);
		when(mapper.map(camareroPL1, Camarero.class)).thenReturn(camarero1);
		when(mapper.map(camareroPL2, Camarero.class)).thenReturn(camarero2);
		
		List<Camarero> camareros = camareroServicesImpl.getByNombreLikeIgnoreCase("orio");
		
		assertEquals(2, camareros.size());
		assertTrue(camareros.containsAll(List.of(camarero1, camarero2)));
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
		
		camareroPL1 = new CamareroPL();
		camareroPL1.setId(1L);
		camareroPL1.setDni("11111111R");
		
		camareroPL2 = new CamareroPL();
		camareroPL2.setId(2L);
		camareroPL2.setDni("22222222L");
		
		// *****************************
		
		camarero1 = new Camarero();
		camarero1.setId(1L);
		camarero1.setDni("11111111R");
		
		camarero2 = new Camarero();
		camarero2.setId(2L);
		camarero2.setDni("22222222L");
		
	}
	
}
