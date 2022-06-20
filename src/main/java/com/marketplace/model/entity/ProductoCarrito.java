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

@Entity
public class ProductoCarrito implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -5477252679878922758L;

	/**
	 * Id ProductoOrden PK
	 */	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Cantidad de Prducto
	 */	
	@Column(nullable = false)
	private Integer cantidadProducto;

	/**
	 *  productoorden y producto
	 */	
	@OneToOne
	@JoinColumn(name = "producto", referencedColumnName = "id",nullable = false)
	private ProductoEntity producto;

	/**
	 * Prodcutos en el carrito
	 */
	@ManyToOne
	@JoinColumn(name = "persona", referencedColumnName = "email",nullable = false)
	private PersonaEntity persona;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the cantidadProducto
	 */
	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
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

}
