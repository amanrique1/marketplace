package com.marketplace.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketplace.model.EstadoPedido;

@Entity
@Table(name="OrdenDeCompra")
public class OrdenDeCompraEntity implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 3788240062826809570L;

	/**
	 * Id Orden de Compra PK
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Fecha de la Compra
	 */
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fecha;

	/**
	 * Estado De la Compra
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoPedido estado;

	/**
	 * productos orden de compra
	 */
	@OneToMany(mappedBy = "ordenDeCompra", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<ProductoOrdenEntity> productosOrden = new ArrayList<ProductoOrdenEntity>();

	/**
	 * Una persona tiene Ordenes de compra
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private PersonaEntity persona;

	/**
	 * Ubicacion de la orden de compra
	 */
	@OneToOne
	@JoinColumn(nullable = false)
	private UbicacionEntity ubicacion;

	/**
	 * Fecha en que se modifico la orden por ultima vez
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaUltimaModificacion;

	/**
	 * Identidificador sobre si necesita revision
	 */
	private Boolean necesitaRevision;

	/**
	 * Lista de estados por los cual ha pasado la orden de compra
	 */
	@OneToMany(mappedBy = "ordenDeCompra",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<CambioEstadoOrdenDeCompraEntity> cambioEstado;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the estado
	 */
	public EstadoPedido getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	/**
	 * @return the productoOrden
	 */
	public List<ProductoOrdenEntity> getProductoOrden() {
		return productosOrden;
	}

	/**
	 * @param productoOrden the productoOrden to set
	 */
	public void setProductoOrden(List<ProductoOrdenEntity> productoOrden) {
		this.productosOrden = productoOrden;
	}
	
	/**
	 * Agrega un nuevo producto a la orden de compra
	 * @param prodOrden a agregar
	 */
	public void agregarProductoOrden(ProductoOrdenEntity prodOrden) {
		productosOrden.add(prodOrden);
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
	 * @return the ubicacion
	 */
	public UbicacionEntity getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(UbicacionEntity ubicacion) {
		this.ubicacion = ubicacion;
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
	 * @return the necesitaRevision
	 */
	public Boolean getNecesitaRevision() {
		return necesitaRevision;
	}

	/**
	 * @param necesitaRevision the necesitaRevision to set
	 */
	public void setNecesitaRevision(Boolean necesitaRevision) {
		this.necesitaRevision = necesitaRevision;
	}

	/**
	 * @return the cambioEstado
	 */
	public List<CambioEstadoOrdenDeCompraEntity> getCambioEstado() {
		return cambioEstado;
	}

	/**
	 * @param cambioEstado the cambioEstado to set
	 */
	public void setCambioEstado(List<CambioEstadoOrdenDeCompraEntity> cambioEstado) {
		this.cambioEstado = cambioEstado;
	}
	
	/**
	 * @param cambioEstado the cambioEstado to add
	 */
	public void agregarCambioEstado(CambioEstadoOrdenDeCompraEntity cambioEstado) {
		this.cambioEstado.add(cambioEstado);
	}
}
