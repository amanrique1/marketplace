package com.marketplace.model.BodyParams;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.Imagen;

public class AgregarCotizacionParams {

	/**
	 * Nombre producto
	 */
	@JsonProperty("name")
	private String nombre;

	/**
	 * Producto base
	 */
	@JsonProperty("baseProduct")
	private Optional<String> productoBase;

	/**
	 * Comentarios
	 */
	@JsonProperty("comments")
	private Optional<String> comentarios;

	/**
	 * Link de referencia
	 */
	@JsonProperty("referenceLink")
	private Optional<String> linkDeReferencia;

	/**
	 * Nombre producto
	 */
	@JsonProperty("referenceAttachement")
	private Optional<String> adjuntoDeReferencia;

	/**
	 * Marca producto
	 */
	@JsonProperty("brand")
	private Optional<String> marca;

	/**
	 * modelo producto
	 */
	@JsonProperty("model")
	private Optional<String> modelo;
	
	/**
	 * Cantidadd a cotizar
	 */
	@JsonProperty("prodQuantity")
	private Optional<Integer> cantProducto;

	/**
	 * Categoria de un producto
	 */
	@JsonProperty("category")
	private Long categoria;

	/**
	 * Categoria de un producto
	 */
	@JsonProperty("provider")
	private String proveedor;

	/**
	 * Imagen del producto
	 */
	@JsonProperty("referenceImage")
	private Optional<Imagen> imagen;

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
	public Optional<String> getProductoBase() {
		return productoBase;
	}

	/**
	 * @param productoBase the productoBase to set
	 */
	public void setProductoBase(Optional<String> productoBase) {
		this.productoBase = productoBase;
	}

	/**
	 * @return the comentarios
	 */
	public Optional<String> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(Optional<String> comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the linkDeReferencia
	 */
	public Optional<String> getLinkDeReferencia() {
		return linkDeReferencia;
	}

	/**
	 * @param linkDeReferencia the linkDeReferencia to set
	 */
	public void setLinkDeReferencia(Optional<String> linkDeReferencia) {
		this.linkDeReferencia = linkDeReferencia;
	}

	/**
	 * @return the adjuntoDeReferencia
	 */
	public Optional<String> getAdjuntoDeReferencia() {
		return adjuntoDeReferencia;
	}

	/**
	 * @param adjuntoDeReferencia the adjuntoDeReferencia to set
	 */
	public void setAdjuntoDeReferencia(Optional<String> adjuntoDeReferencia) {
		this.adjuntoDeReferencia = adjuntoDeReferencia;
	}

	/**
	 * @return the marca
	 */
	public Optional<String> getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(Optional<String> marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public Optional<String> getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(Optional<String> modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the cantProducto
	 */
	public Optional<Integer> getCantProducto() {
		return cantProducto;
	}

	/**
	 * @param cantProducto the cantProducto to set
	 */
	public void setCantProducto(Optional<Integer> cantProducto) {
		this.cantProducto = cantProducto;
	}

	/**
	 * @return the categoria
	 */
	public Long getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the proveedor
	 */
	public String getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the imagen
	 */
	public Optional<Imagen> getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(Optional<Imagen> imagen) {
		this.imagen = imagen;
	}
}
