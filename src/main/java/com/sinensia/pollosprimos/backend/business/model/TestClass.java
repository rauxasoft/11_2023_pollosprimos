package com.sinensia.pollosprimos.backend.business.model;

import java.util.Optional;

public class TestClass {

	public static void main(String[] args) {
		
		Optional<String> optional = Optional.empty();// Optional.of("Pepín");    // 
		
		if(optional.isPresent()) {
			String contenido = optional.get();
			System.out.println(contenido);
		} else {
			System.out.println("el optional viene vacio");
		}
		
		String contenido = optional.orElse("alternativo...");
		
		System.out.println("contenido: " + contenido);
		
		
		

	}

}
