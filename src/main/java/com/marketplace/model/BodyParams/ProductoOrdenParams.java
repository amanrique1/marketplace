package com.marketplace.model.BodyParams;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoOrdenParams {

	/**
	 * Id del producto
	 */
	@JsonProperty("productId")
	private Long idProducto;

	/**
	 * Cantidad del producto
	 */
	@JsonProperty("quantity")
	private Integer cantidad;

	/**
	 * Detalles adicionales del producto
	 */
	@JsonProperty("details")
	private Optional<String> detalles;


	/**
	 * @return the idProducto
	 */
	public Long getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the detalles
	 */
	public Optional<String> getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(Optional<String> detalles) {
		this.detalles = detalles;
	}

}
