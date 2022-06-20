package com.marketplace.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="CambioEstadoOrden")
public class CambioEstadoOrdenDeCompraEntity {

	/**
	 * Id de la relacion del proceso de mover estado
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Estado al cual se movio
	 */
	@Column(nullable = false)
	private String estado;

	/**
	 * Fecha de modificacion
	 */
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaModificacion;

	/**
	 * Persona que cambio el estado
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private UsuarioEntity usuarioModificador;

	/**
	 * Orden de compra a la cual hace referencia
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private OrdenDeCompraEntity ordenDeCompra;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaModificacion
	 */
	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the usuarioModificador
	 */
	public UsuarioEntity getUsuarioModificador() {
		return usuarioModificador;
	}

	/**
	 * @param usuarioModificador the usuarioModificador to set
	 */
	public void setUsuarioModificador(UsuarioEntity usuarioModificador) {
		this.usuarioModificador = usuarioModificador;
	}

	/**
	 * @return the ordenDeCompra
	 */
	public OrdenDeCompraEntity getOrdenDeCompra() {
		return ordenDeCompra;
	}

	/**
	 * @param ordenDeCompra the ordenDeCompra to set
	 */
	public void setOrdenDeCompra(OrdenDeCompraEntity ordenDeCompra) {
		this.ordenDeCompra = ordenDeCompra;
	}
}
