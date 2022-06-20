package com.marketplace.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ubicacion")
public class UbicacionEntity implements Serializable {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -8885444388335039345L;

	/**
	 * Id
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Pais
	 */
	@Column(nullable = false)
	private String pais;

	/**
	 * Ciudad
	 */
	@Column(nullable = false)
	private String ciudad;

	/**
	 * Estado
	 */
	@Column(nullable = false)
	private String estado;

	/**
	 * Direccion de entrega
	 */
	@Column(nullable = false)
	private String direccion;

	/**
	 * Informacion adicional
	 */
	private String detalles;

	/**
	 * Relacion con orden de compra
	 */
	@OneToOne(mappedBy = "ubicacion",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private OrdenDeCompraEntity ordenDeCompra;

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
