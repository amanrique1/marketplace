package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Medidas {

	/**
	 * Anchura del producto
	 */
	@JsonProperty("width")
	private Double anchura;

	/**
	 * Longitud del producto
	 */
	@JsonProperty("height")
	private Double altura;

	/**
	 * Profucndidad del producto
	 */
	@JsonProperty("depth")
	private Double profundidad;

	/**
	 * Unidad de medida
	 */
	@JsonProperty("measureUnit")
	private String unidadDeMedida;

	/**
	 * @return the anchura
	 */
	public Double getAnchura() {
		return anchura;
	}

	/**
	 * @param anchura the anchura to set
	 */
	public void setAnchura(Double anchura) {
		this.anchura = anchura;
	}

	/**
	 * @return the profundidad
	 */
	public Double getProfundidad() {
		return profundidad;
	}

	/**
	 * @param profundidad the profundidad to set
	 */
	public void setProfundidad(Double profundidad) {
		this.profundidad = profundidad;
	}

	/**
	 * @return the unidadDeMedida
	 */
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	/**
	 * @param unidadDeMedida the unidadDeMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	/**
	 * @return the altura
	 */
	public Double getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(Double altura) {
		this.altura = altura;
	}
}
