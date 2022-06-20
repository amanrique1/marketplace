package com.marketplace.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CambioEstado {

	/**
	 * Estado al cual se movio
	 */
	@JsonProperty("state")
	private String estado;

	/**
	 * Fecha de modificacion
	 */
	@JsonProperty("modDate")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaModificacion;

	/**
	 * Persona que cambio el estado
	 */
	@JsonProperty("modifierName")
	private String nombreModificador;
	
	/**
	 * Constructor
	 * @param estado al cual se cambio
	 * @param fechaModificacion
	 * @param nombreModificador
	 */
	public CambioEstado(String estado, LocalDateTime fechaModificacion,String nombreModificador) {
		this.estado = estado;
		this.fechaModificacion = fechaModificacion;
		this.nombreModificador = nombreModificador;
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
	 * @return the nombreModificador
	 */
	public String getNombreModificador() {
		return nombreModificador;
	}

	/**
	 * @param nombreModificador the nombreModificador to set
	 */
	public void setNombreModificador(String nombreModificador) {
		this.nombreModificador = nombreModificador;
	}

}
