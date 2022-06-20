package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Imagen {

	/**
	 * Imagen en base 64
	 */
	@JsonProperty("image")
	private String imagen;

	/**
	 * Fomato de la imagen
	 */
	@JsonProperty("format")
	private String formato;
	
	/**
	 * Constructor
	 * @param imagen en base 64
	 * @param formato de la imagen
	 */
	public Imagen(String imagen, String formato) {
		this.imagen = imagen;
		this.formato = formato;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * @param formatoImagen the formato to set
	 */
	public void setFormato(String formatoImagen) {
		this.formato = formatoImagen;
	}
}
