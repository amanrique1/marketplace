package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Cotizacion {

	/**
	 * Nombre producto
	 */
	@JsonProperty("name")
	private String nombre;

	/**
	 * Producto base
	 */
	@JsonProperty("baseProduct")
	private String productoBase;

	/**
	 * Comentarios
	 */
	@JsonProperty("comments")
	private String comentarios;

	/**
	 * Link de referencia
	 */
	@JsonProperty("referenceLink")
	private String linkDeReferencia;

	/**
	 * Nombre producto
	 */
	@JsonProperty("referenceAttachement")
	private String adjuntoDeReferencia;

	/**
	 * Marca producto
	 */
	@JsonProperty("brand")
	private String marca;

	/**
	 * modelo producto
	 */
	@JsonProperty("model")
	private String modelo;
	
	/**
	 * Cantidadd a cotizar
	 */
	@JsonProperty("prodQuantity")
	private Integer cantProducto;

	/**
	 * Categoria de un producto
	 */
	@JsonProperty("categoryName")
	private String nombreCategoria;

	/**
	 * Categoria de un producto
	 */
	@JsonProperty("providerName")
	private String nombreProveedor;

	/**
	 * Imagen del producto
	 */
	@JsonProperty("referenceImage")
	private Imagen imagen;
	
	public Cotizacion(String nombre, String productoBase,String comentarios,String linkDeReferencia,String adjuntoDeReferencia, String marca, String modelo, Integer cantidadProducto, String nombreCategoria, String nombreProveedor, String imagen, FormatoImagen formato) {
		this.adjuntoDeReferencia = adjuntoDeReferencia;
		this.cantProducto = cantidadProducto;
		this.comentarios = comentarios;
		this.imagen = new Imagen(imagen, formato.toString());
		this.linkDeReferencia = linkDeReferencia;
		this.marca = marca;
		this.modelo = modelo;
		this.nombre = nombre;
		this.nombreCategoria = nombreCategoria;
		this.nombreProveedor = nombreProveedor;
		this.productoBase = productoBase;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the productoBase
	 */
	public String getProductoBase() {
		return productoBase;
	}

	/**
	 * @param productoBase the productoBase to set
	 */
	public void setProductoBase(String productoBase) {
		this.productoBase = productoBase;
	}

	/**
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the linkDeReferencia
	 */
	public String getLinkDeReferencia() {
		return linkDeReferencia;
	}

	/**
	 * @param linkDeReferencia the linkDeReferencia to set
	 */
	public void setLinkDeReferencia(String linkDeReferencia) {
		this.linkDeReferencia = linkDeReferencia;
	}

	/**
	 * @return the adjuntoDeReferencia
	 */
	public String getAdjuntoDeReferencia() {
		return adjuntoDeReferencia;
	}

	/**
	 * @param adjuntoDeReferencia the adjuntoDeReferencia to set
	 */
	public void setAdjuntoDeReferencia(String adjuntoDeReferencia) {
		this.adjuntoDeReferencia = adjuntoDeReferencia;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the cantProducto
	 */
	public Integer getCantProducto() {
		return cantProducto;
	}

	/**
	 * @param cantProducto the cantProducto to set
	 */
	public void setCantProducto(Integer cantProducto) {
		this.cantProducto = cantProducto;
	}

	/**
	 * @return the categoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	/**
	 * @return the nombreProveedor
	 */
	public String getNombreProveedor() {
		return nombreProveedor;
	}

	/**
	 * @param nombreProveedor the nombreProveedor to set
	 */
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	/**
	 * @return the imagen
	 */
	public Imagen getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}
}
