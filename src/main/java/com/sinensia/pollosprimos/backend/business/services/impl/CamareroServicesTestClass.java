package com.sinensia.pollosprimos.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.services.CamareroServices;

public class CamareroServicesTestClass {

	public static void main(String[] args) {
		
		printBanner("Prueba del método read()");
		
		CamareroServices camareroServices = new CamareroServicesImpl();
		
		Optional<Camarero> optional1 = camareroServices.read(10L);
		
		System.out.println(optional1.orElse(null));

		// *************************************************************************
		
		printBanner("Prueba del método getAll()");
		
		List<Camarero> camareros = camareroServices.getAll();
		
		for(Camarero camarero: camareros) {
			System.out.println(camarero);
		}
		
		// *************************************************************************
		
		printBanner("Prueba del método ....");
		
		// *************************************************************************
		
	}
	
	private static void printBanner(String titulo) {
		
		System.out.println("\n*****************************************************");
		System.out.println("---> " + titulo);
		System.out.println("****************************************************\n");	
	}

}
