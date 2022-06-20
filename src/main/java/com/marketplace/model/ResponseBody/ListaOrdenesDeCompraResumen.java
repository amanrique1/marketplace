package com.marketplace.model.ResponseBody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.OrdenDeCompra;

public class ListaOrdenesDeCompraResumen {

	/**
	 * Fecha de la Compra
	 */
	@JsonProperty("purchaseOrders")
	private List<OrdenDeCompra> ordenes;

	public ListaOrdenesDeCompraResumen(List<OrdenDeCompra> ordenes) {
		this.ordenes = ordenes;
	}
}
