package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProductoOrdenDetalle {

	/**
	 * Id del producto
	 */
	@JsonProperty("product")
	private Producto producto;

	/**
	 * Cantidad del producto
	 */
	@JsonProperty("quantity")
	private Integer cantidad;

	/**
	 * Detalles adicional del producto
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
	 * @param id del producto
	 * @param nombre del producto
	 * @param precioTotal del producto
	 * @param valorImpuesto del producto
	 * @param moneda del producto
	 * @param stock del producto
	 * @param marca del producto
	 * @param modelo del producto
	 * @param anchura del producto
	 * @param altura del producto
	 * @param profundidad del producto
	 * @param unidadDeMedida del producto
	 * @param peso del producto
	 * @param unidadDePeso
	 * @param imagen del producto
	 * @param formatoImagen del producto
	 * @param cantidad del producto
	 * @param detalles de la orden de compra
	 * @param valorTotal de todos los productos
	 * @param unidadMonetaria del producto
	 */
	public ProductoOrdenDetalle(Long id,String nombre,Double precioTotal,Double valorImpuesto,String moneda,Integer stock,String marca,String modelo,Double peso,String unidadDePeso,Double anchura,Double altura,Double profundidad,String unidadDeMedida,String imagen,FormatoImagen formatoImagen,Integer cantidad, String detalles,Double valorTotal,String unidadMonetaria) {
		this.producto = new Producto(id, nombre, precioTotal, valorImpuesto, moneda, stock, marca, modelo, peso, unidadDePeso, anchura, altura, profundidad, unidadDeMedida, imagen, formatoImagen);
		this.cantidad = cantidad;
		this.detalles = detalles;
		this.valorTotal = valorTotal;
		this.unidadMonetaria = unidadMonetaria;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
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

}
