package com.marketplace.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.marketplace.model.FormatoImagen;

@Entity
@Table(name="Imagen")
public class ImagenEntity implements Serializable{

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -3756640300086265615L;

	/**
	 * Id de la imagen
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Imagen en base 64
	 */
	@Column(columnDefinition = "TEXT")
	private String imagen;

	/**
	 * Fomato de la imagen
	 */
	@Enumerated(EnumType.STRING)
	private FormatoImagen formatoImagen;

	/**
	 * Producto
	 */
	@OneToOne(mappedBy = "imagen",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private ProductoEntity producto;
	
	/**
	 * Producto
	 */
	@OneToOne(mappedBy = "imagenReferencia",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private CotizacionEntity Cotizacion;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the formatoImagen
	 */
	public FormatoImagen getFormatoImagen() {
		return formatoImagen;
	}

	/**
	 * @param formatoImagen the formatoImagen to set
	 */
	public void setFormatoImagen(FormatoImagen formatoImagen) {
		this.formatoImagen = formatoImagen;
	}

	/**
	 * @return the producto
	 */
	public ProductoEntity getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
}