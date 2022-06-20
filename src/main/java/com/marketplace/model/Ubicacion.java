package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Ubicacion {

	/**
	 * Pais
	 */
	@JsonProperty("country")
	private String pais;

	/**
	 * Ciudad
	 */
	@JsonProperty("city")
	private String ciudad;

	/**
	 * Estado
	 */
	@JsonProperty("state")
	private String estado;

	/**
	 * Direccion de entrega
	 */
	@JsonProperty("address")
	private String direccion;

	/**
	 * Informacion adicional
	 */
	@JsonProperty("details")
	private String detalles;

	/**
	 * Constructor
	 * @param pais
	 * @param estado
	 * @param ciudad
	 * @param direccion
	 * @param detalles
	 */
	public Ubicacion(String pais,String estado,String ciudad,String direccion,String detalles) {
		this.pais = pais;
		this.estado = estado;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.detalles = detalles;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	/**
	 * @return the detalles
	 */
	public String getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
}
