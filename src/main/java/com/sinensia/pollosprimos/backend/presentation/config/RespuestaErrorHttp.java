package com.sinensia.pollosprimos.backend.presentation.config;

import java.io.Serializable;

public class RespuestaErrorHttp implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String error;
	
	public RespuestaErrorHttp(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		return "RespuestaErrorHttp [error=" + error + "]";
	}
	
}
