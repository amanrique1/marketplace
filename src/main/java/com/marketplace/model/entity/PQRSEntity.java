package com.marketplace.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketplace.model.EstadosPQRS;
import com.marketplace.model.TipoPQRS;

@Entity
@Table(name="PQS")
public class PQRSEntity  implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 8526519584311076611L;

	/**
	 * Id PQS PK
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Descrpcion PQS
	 */
	@Column(nullable = false)
	private String descripcion;

	/**
	 * Tipo de PQS
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoPQRS tipo;

	/**
	 * Estado PQS
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadosPQRS estado;

	/**
	 * reclamo sobre producto
	 */
	@OneToOne
	private ProductoOrdenEntity productoOrden;

	/**
	 * Persona y sus pqs
	 */
	@ManyToOne
	private UsuarioEntity usuario;

	/**
	 * Fecha en que se modifico la orden por ultima vez
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaUltimaModificacion;

	/**
	 * Lista de estados por los cual ha pasado la orden de compra
	 */
	@OneToMany(mappedBy = "pqs",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<CambioEstadoPQRSEntity> cambioEstado;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tipo
	 */
	public TipoPQRS getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoPQRS tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the estado
	 */
	public EstadosPQRS getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadosPQRS estado) {
		this.estado = estado;
	}

	/**
	 * @return the productoorden
	 */
	public ProductoOrdenEntity getProductoOrden() {
		return productoOrden;
	}

	/**
	 * @param productoorden the productoorden to set
	 */
	public void setProductoOrden(ProductoOrdenEntity productoorden) {
		this.productoOrden = productoorden;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioEntity getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the fechaUltimaModificacion
	 */
	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	/**
	 * @param fechaUltimaModificacion the fechaUltimaModificacion to set
	 */
	public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	/**
	 * @return the cambioEstado
	 */
	public List<CambioEstadoPQRSEntity> getCambioEstado() {
		return cambioEstado;
	}

	/**
	 * @param cambioEstado the cambioEstado to set
	 */
	public void setCambioEstado(List<CambioEstadoPQRSEntity> cambioEstado) {
		this.cambioEstado = cambioEstado;
	}

	/**
	 * @param cambioEstado the cambioEstado to add
	 */
	public void agregarCambioEstado(CambioEstadoPQRSEntity cambioEstado) {
		this.cambioEstado.add(cambioEstado);
	}
}
