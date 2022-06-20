package com.marketplace.model.BodyParams;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CambioEstadoParams {
	
	/**
	 * Nombre de la categoria
	 */
	@JsonProperty("newState")
	private String nuevoEstado;

	/**
	 * @return the nuevoEstado
	 */
	public String getNuevoEstado() {
		return nuevoEstado;
	}

	/**
	 * @param nuevoEstado the nuevoEstado to set
	 */
	public void setNuevoEstado(String nuevoEstado) {
		this.nuevoEstado = nuevoEstado;
	}

}
