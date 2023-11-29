package com.sinensia.pollosprimos.backend.common;

import java.util.List;

public class Pagina<T> {
	
	// Podemos...
	
	// 1. Dejarlo tal como está
	// 2. Añadir un super-constructor que lo reciba todo
	// 3. Aplicacar el patrón builder (super advanced a la vez que interesante)
	
	private List<T> elementos;
	private int numeroPagina;
	private int numeroElementos;		// De página
	private long numeroTotalElementos;	// Total en la DB
	private int numeroTotalPaginas;
	private boolean primeraPagina;
	private boolean ultimaPagina;
	
	public Pagina() {
		
	}

	public List<T> getElementos() {
		return elementos;
	}

	public void setElementos(List<T> elementos) {
		this.elementos = elementos;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public int getNumeroElementos() {
		return numeroElementos;
	}

	public void setNumeroElementos(int numeroElementos) {
		this.numeroElementos = numeroElementos;
	}

	public long getNumeroTotalElementos() {
		return numeroTotalElementos;
	}

	public void setNumeroTotalElementos(long numeroTotalElementos) {
		this.numeroTotalElementos = numeroTotalElementos;
	}

	public int getNumeroTotalPaginas() {
		return numeroTotalPaginas;
	}

	public void setNumeroTotalPaginas(int numeroTotalPaginas) {
		this.numeroTotalPaginas = numeroTotalPaginas;
	}

	public boolean isPrimeraPagina() {
		return primeraPagina;
	}

	public void setPrimeraPagina(boolean primeraPagina) {
		this.primeraPagina = primeraPagina;
	}

	public boolean isUltimaPagina() {
		return ultimaPagina;
	}

	public void setUltimaPagina(boolean ultimaPagina) {
		this.ultimaPagina = ultimaPagina;
	}
	
}
