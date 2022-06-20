package com.marketplace.model.BodyParams;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrearPQRSParams {

	/**
	 * Descrpcion PQS
	 */
	@JsonProperty("details")
	private String descripcion;

	/**
	 * Tipo de PQS
	 */
	@JsonProperty("type")
	private String tipo;
	
	/**
	 * Tipo de PQS
	 */
	@JsonProperty("prodOrderId")
	private Optional<Long> idOrdenProducto;

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the idOrdenProducto
	 */
	public Optional<Long> getIdOrdenProducto() {
		return idOrdenProducto;
	}

	/**
	 * @param idOrdenProducto the idOrdenProducto to set
	 */
	public void setIdOrdenProducto(Optional<Long> idOrdenProducto) {
		this.idOrdenProducto = idOrdenProducto;
	}
}
