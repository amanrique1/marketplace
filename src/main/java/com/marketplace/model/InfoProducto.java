package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoProducto {

	/**
	 * Id del producto
	 */
	private Long id;

	/**
	 * Nombre del producto
	 */
	@JsonProperty("name")
	private String nombre;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	 * Constructor
	 */
	public InfoProducto(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
}