package com.sinensia.pollosprimos.backend.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.pollosprimos.backend.integration.model.ProductoPL;

@DataJpaTest
@Sql(scripts={"/data/h2/schema_drop_objects_test.sql",
		      "/data/h2/schema_categorias_test.sql", 
			  "/data/h2/schema_test.sql",
			  "/data/h2/data_categorias_test.sql",
		      "/data/h2/data_test.sql"})
class ProductoPLRepositoryTest {
	
	@Autowired
	private ProductoPLRepository productoPLRepository;

	@Test
	@Disabled("Pendiente de implementación")
	void findByPrecioBetweenOrderByPrecio(){
		fail("Not implemented yet!");
	}
	
	@Test
	@Disabled("Pendiente de implementación")
	void findByFechaAltaBetweenOrderByFechaAlta() {
		fail("Not implemented yet!");
	}
	
	@Test
	void findByDescatalogadoTrue() {
		
		ProductoPL productoPL = new ProductoPL();
		productoPL.setCodigo(136L);
		
		List<ProductoPL> productosPL = productoPLRepository.findByDescatalogadoTrue();
		
		assertEquals(1, productosPL.size());
		assertTrue(productosPL.containsAll(List.of(productoPL)));
		
	} 
	
	@Test
	@Disabled("Pendiente de implementación")
	void findByDescatalogado() {
		fail("Not implemented yet!");
	} 

	@Test
	@Disabled("Pendiente de implementación")
	void findByCategoria() {
		fail("Not implemented yet!");
	}
	
	@Test
	@Disabled("Pendiente de implementación")
	void variarPrecio_v1() {
		fail("Not implemented yet!");
	}
	
	@Test
	@Disabled("Pendiente de implementación")
	void variarPrecio_v2() {
		fail("Not implemented yet!");
	}
	
	@Test
	@Disabled("Pendiente de implementación")
	void getEstadisticaNumeroProductos() {
		fail("Not implemented yet!");
	}
	
	@Test
	@Disabled("Pendiente de implementación")
	void getEstadisticaPrecioMedio() {
		fail("Not implemented yet!");
	}
	
}
