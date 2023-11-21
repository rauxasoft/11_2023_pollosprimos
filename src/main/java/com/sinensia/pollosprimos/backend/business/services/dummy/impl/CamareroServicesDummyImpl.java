package com.sinensia.pollosprimos.backend.business.services.dummy.impl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.pollosprimos.backend.business.model.Camarero;
import com.sinensia.pollosprimos.backend.business.model.DatosContacto;
import com.sinensia.pollosprimos.backend.business.model.Direccion;
import com.sinensia.pollosprimos.backend.business.services.CamareroServices;

@Service
public class CamareroServicesDummyImpl implements CamareroServices {

	private final Map<Long, Camarero> CAMAREROS = new HashMap<>();
	
	public CamareroServicesDummyImpl() {
		initObjects();
	}
	
	@Override
	public Long create(Camarero camarero) {
		
		if(camarero.getId() != null) {
			throw new IllegalStateException("Para poder crear un camarero su id ha de ser null");
		}
		
		String dni = camarero.getDni();
		
		for(Long id: CAMAREROS.keySet()) {
			if(CAMAREROS.get(id).getDni().equals(dni)) {
				throw new IllegalStateException("Ya existe un camarero con dni " + dni);
			}
		}
		
		Long nuevoId = System.currentTimeMillis();
		camarero.setId(nuevoId);
		CAMAREROS.put(nuevoId, camarero);
		
		return nuevoId;
	}

	@Override
	public Optional<Camarero> read(Long id) {
		return Optional.ofNullable(CAMAREROS.get(id));
	}

	@Override
	public Optional<Camarero> read(String dni) {
		
		for(Camarero camarero: CAMAREROS.values()) {
			if (camarero.getDni().equals(dni)) {
				return Optional.of(camarero);
			}
		}
		
		return Optional.empty();
	}

	@Override
	public void update(Camarero camarero) {
	
		Long id = camarero.getId();
		
		if(id == null) {
			throw new IllegalArgumentException("No se puede actualizar un camarero con id null");
		}
		
		boolean existe = CAMAREROS.containsKey(id);
		
		if(!existe) {
			throw new IllegalStateException("No existe el camarero con id " + id + " No se ha podido actualizar.");
		}
		
		CAMAREROS.replace(id, camarero);
		
	}
	
	@Override
	public void delete(Long id) {
		
		boolean existe = CAMAREROS.containsKey(id);
		
		if(!existe) {
			throw new IllegalStateException("No se puede eliminar el camarero " + id + ". No existe!");
		}
		
		CAMAREROS.remove(id);
	}

	@Override
	public List<Camarero> getAll() {
		return new ArrayList<>(CAMAREROS.values());
	}

	@Override
	public List<Camarero> getByNombreLikeIgnoreCase(String texto) {
		
		List<Camarero> camareros = new ArrayList<>();
		
		for(Camarero camarero: CAMAREROS.values()) {
			if (camarero.getNombre().toUpperCase().contains(texto.toUpperCase())) {
				camareros.add(camarero);
			}
		}
		
		return camareros;
	}

	@Override
	public int getNumeroTotalCamareros() {
		return CAMAREROS.size();
	}
	
	// ********************************************************
	//
	// Private Methods
	//
	// ********************************************************
	
	private void initObjects() {
		
		Camarero camarero1 = new Camarero();
		camarero1.setId(10L);
		camarero1.setDni("49923212R");
		camarero1.setNombre("Pepín");
		camarero1.setApellido1("Gálvez");
		camarero1.setApellido2("Ridruejo");
		Direccion direccion = new Direccion();
		DatosContacto datosContacto = new DatosContacto();
		direccion.setDireccion("Avda. de la Luz, 230");
		direccion.setPoblacion("Santa Perpetua de Mogoda");
		direccion.setCodigoPostal("08034");
		direccion.setProvincia("Barcelona");
		direccion.setPais("España");
		datosContacto.setTelefono("+34 6209087");
		datosContacto.setEmail("pepin893@gmail.com");
		camarero1.setDireccion(direccion);
		camarero1.setDatosContacto(datosContacto);
		
		// *******************************************************
		
		Camarero camarero2 = new Camarero();
		camarero2.setId(11L);
		camarero2.setDni("39115482J");
		camarero2.setNombre("Anna");
		camarero2.setApellido1("Vilvhez");
		camarero2.setApellido2("Losada");
		Direccion direccion2 = new Direccion();
		DatosContacto datosContacto2 = new DatosContacto();
		direccion2.setDireccion("c/ Fernando Esteso, 10");
		direccion2.setPoblacion("Madrid");
		direccion2.setCodigoPostal("80970");
		direccion2.setProvincia("Madrid");
		direccion2.setPais("España");
		datosContacto2.setTelefono("+34 2218976");
		datosContacto2.setEmail("annavilvhez@gmail.com");
		camarero2.setDireccion(direccion2);
		camarero2.setDatosContacto(datosContacto2);
		
		CAMAREROS.put(camarero1.getId(), camarero1);
		CAMAREROS.put(camarero2.getId(), camarero2);
		
	}
}
