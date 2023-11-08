package com.sinensia.pollosprimos.backend.business.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BorrameTestClass {

	public static void main(String[] args) throws Exception {
		
		Cliente cliente = new Cliente();
		
		Direccion direccion = new Direccion();
		DatosContacto datosContacto = new DatosContacto();
		
		direccion.setDireccion("Avda. de los Pinos, 34");
		direccion.setPoblacion("Santa Perpetua de Mogoda");
		datosContacto.setEmail("peping556@gmail.com");
		
		cliente.setId(100L);
		cliente.setDni("3787762K");
		cliente.setNombre("Pepín");
		cliente.setApellido1("Gálvez");
		cliente.setDireccion(direccion);
		cliente.setDatosContacto(datosContacto);
		cliente.setTarjetaGold(true);
		
		// ATENCIÓN !!!!!
		// Vamos a deserializar el cliente para que persista en forma de fichero
		
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Admin\\prueba_xxxxxxxxxxxxx.obj");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(cliente);
		oos.flush();
		
		oos.close();
		
		// ***********************************************
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\prueba_xxxxxxxxxxxxx.obj");
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Cliente clienteLeido = null;
		
		clienteLeido = (Cliente) ois.readObject();
		
		ois.close();
		
		System.out.println(clienteLeido);
	
	}

}
