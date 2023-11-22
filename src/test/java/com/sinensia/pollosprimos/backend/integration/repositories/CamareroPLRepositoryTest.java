package com.sinensia.pollosprimos.backend.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.pollosprimos.backend.integration.model.CamareroPL;

@DataJpaTest
@Sql(scripts={"/data/h2/schema_test.sql","/data/h2/data_test.sql"})
public class CamareroPLRepositoryTest {

	@Autowired
	private CamareroPLRepository camareroPLRepository;
	
	@Test
	void existeByDniTest() {
		
		boolean existe1 = camareroPLRepository.existsByDni("30092765K"); // si
		boolean existe2 = camareroPLRepository.existsByDni("45899812L"); // no
		
		assertTrue(existe1);
		assertFalse(existe2);
	}
	
	@Test
	void findByDniTest() {
		
		CamareroPL camareroPL1 = camareroPLRepository.findByDni("30092765K");
		CamareroPL camareroPL2 = camareroPLRepository.findByDni("45899812L");
		
		assertNotNull(camareroPL1);
		assertEquals(11L, camareroPL1.getId());
		assertEquals("José Ramón", camareroPL1.getNombre());
		
		assertNull(camareroPL2);
	}
	
	@Test
	void findByNombreLikeIgnoreCaseTest() {
		
		List<CamareroPL> camarerosPL1 = camareroPLRepository.findByNombreLikeIgnoreCase("Er");
		List<CamareroPL> camarerosPL2 = camareroPLRepository.findByNombreLikeIgnoreCase("**** NO EXISTE ****");
		
		CamareroPL camareroPL1 = new CamareroPL();
		CamareroPL camareroPL2 = new CamareroPL();
		
		camareroPL1.setId(13L);
		camareroPL2.setId(15L);

		List<CamareroPL> camarerosEsperados = List.of(camareroPL1, camareroPL2);
		
		assertEquals(2, camarerosPL1.size());
		assertTrue(camarerosPL1.containsAll(camarerosEsperados));
		
		assertEquals(0, camarerosPL2.size());
		
	}
	
}
