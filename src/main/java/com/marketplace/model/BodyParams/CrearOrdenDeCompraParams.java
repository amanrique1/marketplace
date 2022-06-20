package com.marketplace.model.BodyParams;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.Ubicacion;

public class CrearOrdenDeCompraParams {
	
	/**
	 * Nombre de la categoria
	 */
	@JsonProperty("orderProducts")
	private List<ProductoOrdenParams> ordenProductos;
	
	/**
	 * Ubicacion de la orden de compra
	 */
	@JsonProperty("deliveryAddress")
	private Ubicacion ubicacion;
	
	/**
	 * Necesita revison
	 */
	@JsonProperty("reviewNeeded")
	private Boolean necesitaRevision;
	
	/**
	 * Valor total de la compra
	 */
	@JsonProperty("totalPrice")
	private Double valorTotal;

	/**
	 * @return the ordenProductos
	 */
	public List<ProductoOrdenParams> getOrdenProductos() {
		return ordenProductos;
	}

	/**
	 * @param ordenProductos the ordenProductos to set
	 */
	public void setOrdenProductos(List<ProductoOrdenParams> ordenProductos) {
		this.ordenProductos = ordenProductos;
	}

	/**
	 * @return the ubicacion
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the necesitaRevision
	 */
	public Boolean getNecesitaRevision() {
		return necesitaRevision;
	}

	/**
	 * @param necesitaRevision the necesitaRevision to set
	 */
	public void setNecesitaRevision(Boolean necesitaRevision) {
		this.necesitaRevision = necesitaRevision;
	}

	/**
	 * @return the valorTotal
	 */
	public Double getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
