package com.marketplace.model.ResponseBody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.InfoProducto;

public class DarInfoProductos {

	/**
	 * Lista de productos
	 */
	@JsonProperty("products")
	private List<InfoProducto> productos;
	
	/**
	 * Constructor
	 * @param productos
	 */
	public DarInfoProductos(List<InfoProducto> productos) {
		this.productos = productos;
	}

	/**
	 * @return the productos
	 */
	public List<InfoProducto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<InfoProducto> productos) {
		this.productos = productos;
	}
}
