package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Categoria {

	/**
	 * Id de la categoria
	 */
	private Long id;

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
	 * Constructor
	 */
	public Categoria(Long id, String nombre, String icono) {
		this.id = id;
		this.nombre = nombre;
		this.icono = icono;
	}

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
