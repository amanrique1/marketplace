package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoOrden {

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
	 * Valor total de todos los productos
	 */
	@JsonProperty("totalValue")
	private Double valorTotal;

	/**
	 * Unidad monetaria de la orden de compra
	 */
	@JsonProperty("currency")
	private String unidadMonetaria;

	/**
	 * Constructor
	 * @param id del producto de la orden
	 * @param cantidad del producto
	 */
	public ProductoOrden(Long id,String nombre,Double valorTotal,String unidadMonetaria,String imagen,FormatoImagen formato,Integer cantidad, String detalles) {
		this.setInfoProducto(new InfoProductoOrdenCompra(id, nombre,imagen,formato));
		this.cantidad = cantidad;
		this.detalles = detalles;
		this.valorTotal = valorTotal;
		this.unidadMonetaria = unidadMonetaria;
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

	/**
	 * @return the unidadMonetaria
	 */
	public String getUnidadMonetaria() {
		return unidadMonetaria;
	}

	/**
	 * @param unidadMonetaria the unidadMonetaria to set
	 */
	public void setUnidadMonetaria(String unidadMonetaria) {
		this.unidadMonetaria = unidadMonetaria;
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
