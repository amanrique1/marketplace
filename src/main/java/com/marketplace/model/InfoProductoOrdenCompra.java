package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoProductoOrdenCompra {

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
	 * Imagen del producto
	 */
	@JsonProperty("image")
	private Imagen imagen;

	/**
	 * Constructor
	 */
	public InfoProductoOrdenCompra(Long id, String nombre,String imagen,FormatoImagen formato) {
		this.id = id;
		this.nombre = nombre;
		this.imagen = new Imagen(imagen,formato.toString());
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
}