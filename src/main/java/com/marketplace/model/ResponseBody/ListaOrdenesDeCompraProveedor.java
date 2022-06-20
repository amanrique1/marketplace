package com.marketplace.model.ResponseBody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.OrdenDeCompraProveedor;

public class ListaOrdenesDeCompraProveedor {

	/**
	 * Fecha de la Compra
	 */
	@JsonProperty("purchaseOrders")
	private List<OrdenDeCompraProveedor> ordenes;

	public ListaOrdenesDeCompraProveedor(List<OrdenDeCompraProveedor> ordenes) {
		this.ordenes = ordenes;
	}
}
