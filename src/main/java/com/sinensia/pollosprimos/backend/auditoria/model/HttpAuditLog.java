package com.sinensia.pollosprimos.backend.auditoria.model;

import java.io.Serializable;
import java.util.Objects;

public class HttpAuditLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long entrada;
	private Long elapsedTime; // tiempo transcurrido entre entrada y salida
	private String verbo;
	private String remoteIP;
	private String recurso;
	private int httpStatusCode;
	
	public HttpAuditLog() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntrada() {
		return entrada;
	}

	public void setEntrada(Long entrada) {
		this.entrada = entrada;
	}

	public Long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getVerbo() {
		return verbo;
	}

	public void setVerbo(String verbo) {
		this.verbo = verbo;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HttpAuditLog other = (HttpAuditLog) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "HttpAuditLog [id=" + id + ", entrada=" + entrada + ", elapsedTime=" + elapsedTime + ", verbo=" + verbo
				+ ", remoteIP=" + remoteIP + ", recurso=" + recurso + ", httpStatusCode=" + httpStatusCode + "]";
	}

}
