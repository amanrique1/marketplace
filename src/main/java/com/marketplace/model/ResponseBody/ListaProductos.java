package com.marketplace.model.ResponseBody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.Producto;

public class ListaProductos {

	/**
	 * Lista de categorias
	 */
	@JsonProperty("products")
	private List<Producto> productos;
	
	/**
	 * Constructor
	 * @param productos
	 */
	public ListaProductos(List<Producto> productos) {
		this.productos = productos;
	}

	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}