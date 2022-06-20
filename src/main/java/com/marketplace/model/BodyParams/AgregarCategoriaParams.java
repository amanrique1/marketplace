package com.marketplace.model.BodyParams;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgregarCategoriaParams {

	/**
	 * Nombre de la categoria
	 */
	@JsonProperty("name")
	private String nombre;

	/**
	 * Icono de la categoria
	 */
	@JsonProperty("icon")
	private String icono;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * @param icono the icono to set
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}
}
