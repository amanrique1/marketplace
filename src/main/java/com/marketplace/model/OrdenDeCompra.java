package com.marketplace.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrdenDeCompra {

	/**
	 * Id Orden de Compra PK
	 */
	private Long id;

	/**
	 * Fecha de la Compra
	 */
	@JsonProperty("creationDate")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fecha;

	/**
	 * Estado De la Compra
	 */
	@JsonProperty("state")
	private EstadoPedido estado;

	/**
	 * productos orden de compra con nombre incluido
	 */
	@JsonProperty("orderProducts")
	private List<ProductoOrden> productosOrden;

	/**
	 * Ubicacion de la orden de compra
	 */
	@JsonProperty("location")
	private Ubicacion ubicacion;

	/**
	 * Fecha en que se modifico la orden por ultima vez
	 */
	@JsonProperty("lastModDate")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaUltimaModificacion;

	/**
	 * Lista de estados por los cual ha pasado la orden de compra
	 */
	@JsonProperty("stateChanges")
	private List<CambioEstado> cambiosEstado;

	/**
	 * Constructor
	 * @param id de la orden de compra
	 * @param fecha de la orden de compra
	 * @param fechaUltModificacion de la orden de compra
	 * @param estado de la orden de compra
	 * @param productos de la orden de compra
	 * @param ubicacion de entrega de la orden de compra
	 * @param cambios cambios de estado de la orden de compra
	 */
	public OrdenDeCompra(Long id, LocalDateTime fecha, LocalDateTime fechaUltModificacion, Double valorTotal,EstadoPedido estado, List<ProductoOrden> productos,Ubicacion ubicacion,List<CambioEstado> cambios) {
		this.id = id;
		this.fecha = fecha;
		this.fechaUltimaModificacion = fechaUltModificacion;
		this.estado = estado;
		this.productosOrden = productos;
		this.ubicacion = ubicacion;
		this.setCambiosEstado(cambios);
	}

	/**
	 * Constructor
	 * @param id de la orden de compra
	 * @param fecha de la orden de compra
	 * @param fechaUltModificacion de la orden de compra
	 * @param estado de la orden de compra
	 */
	public OrdenDeCompra(Long id, LocalDateTime fecha, LocalDateTime fechaUltModificacion,EstadoPedido estado) {
		this.id = id;
		this.fecha = fecha;
		this.fechaUltimaModificacion = fechaUltModificacion;
		this.estado = estado;
	}

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
	 * @return the productoOrden
	 */
	public List<ProductoOrden> getProductosOrden() {
		return productosOrden;
	}

	/**
	 * @param productoOrden the productoOrden to set
	 */
	public void setProductosOrden(List<ProductoOrden> productoOrden) {
		this.productosOrden = productoOrden;
	}

	/**
	 * @return the ubicacion
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the cambiosEstado
	 */
	public List<CambioEstado> getCambiosEstado() {
		return cambiosEstado;
	}

	/**
	 * @param cambiosEstado the cambiosEstado to set
	 */
	public void setCambiosEstado(List<CambioEstado> cambiosEstado) {
		this.cambiosEstado = cambiosEstado;
	}
}
