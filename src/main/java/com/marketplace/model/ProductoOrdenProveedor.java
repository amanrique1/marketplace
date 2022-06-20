package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoOrdenProveedor {

	/**
	 * Id del producto
	 */
	@JsonProperty("product")
	private InfoProductoOrdenCompra infoProducto;

	/**
	 * Cantidad del producto
	 */
	@JsonProperty("quantity")
	private Integer cantidad;

	/**
	 * Detalles adicionales del producto
	 */
	@JsonProperty("details")
	private String detalles;

	/**
	 * Constructor
	 * @param id del producto de la orden
	 * @param cantidad del producto
	 */
	public ProductoOrdenProveedor(Long id,String nombre,String imagen,FormatoImagen formato,Integer cantidad, String detalles) {
		this.setInfoProducto(new InfoProductoOrdenCompra(id, nombre,imagen,formato));
		this.cantidad = cantidad;
		this.detalles = detalles;
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
	public String getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	/**
	 * @return the infoProducto
	 */
	public InfoProductoOrdenCompra getInfoProducto() {
		return infoProducto;
	}

	/**
	 * @param infoProducto the infoProducto to set
	 */
	public void setInfoProducto(InfoProductoOrdenCompra infoProducto) {
		this.infoProducto = infoProducto;
	}

}
