package com.marketplace.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cotizacion")
public class CotizacionEntity implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -4204605517743447887L;

	/**
	 * Id Requerimiento PK
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre del producto
	 */
	@Column(nullable = false)
	private String nombre;

	/**
	 * Nombre Producto base
	 */
	private String productoBase;

	/**
	 * Descripcion del producto
	 */
	private String comentarios;

	/**
	 * Link referencia
	 */
	private String linkDeReferencia;

	/**
	 * Marca del producto a cotizar
	 */
	private String marca;

	/**
	 * Modelo del producto a cotizar
	 */
	private String modelo;

	/**
	 * Cantidad deseada de producto
	 */
	private Integer cantidadProducto;

	/**
	 * Imagen Producto
	 */
	@OneToOne
	private ImagenEntity imagenReferencia;

	/**
	 * Adjunto
	 */
	private String adjuntoDeReferencia;

	/**
	 * Categoria de un producto
	 */
	@ManyToOne
	@JoinColumn(nullable=false)
	private CategoriaEntity categoria;

	/**
	 * Requeriminetos de una persona
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private PersonaEntity persona;

	/**
	 * requerminetos del proveedor
	 */
	@OneToOne
	@JoinColumn(name = "proveedor", referencedColumnName = "email",nullable = false)
	private ProveedorEntity proveedor;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	 * @return the persona
	 */
	public PersonaEntity getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(PersonaEntity persona) {
		this.persona = persona;
	}

	/**
	 * @return the proveedor
	 */
	public ProveedorEntity getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
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
	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	/**
	 * @param cantProducto the cantProducto to set
	 */
	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	/**
	 * @return the imagenReferencia
	 */
	public ImagenEntity getImagenReferencia() {
		return imagenReferencia;
	}

	/**
	 * @param imagenReferencia the imagenReferencia to set
	 */
	public void setImagenReferencia(ImagenEntity imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
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
	 * @return the categoria
	 */
	public CategoriaEntity getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

}
