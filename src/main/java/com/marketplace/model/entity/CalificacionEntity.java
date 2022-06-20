package com.marketplace.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Calificacion")
public class CalificacionEntity implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 410482845118884751L;

	/**
	 * ID del proveedor PK
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Califiacion del Proveedor
	 */
	@Column(nullable = false)
	private Integer calificacion;

	/**
	 * Comentarios
	 */
	@Column(nullable = false)
	private String comentario;

	/**
	 * Calificaciones de un proveedor
	 */
	@ManyToOne
	@JoinColumn(name = "proveedor", referencedColumnName = "email",nullable = false)
	private ProveedorEntity proveedor;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the calificacion
	 */
	public Integer getCalificacion() {
		return calificacion;
	}

	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
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

}
