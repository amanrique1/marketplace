package com.marketplace.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Categoria")
public class CategoriaEntity implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7444554847157976154L;

	/**
	 * ID carrito PK
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre de la categoria
	 */
	@Column(nullable = false)
	private String nombre;

	/**
	 * Icono de la categoria
	 */
	@Column(nullable = false)
	private String icono;

	/**
	 * Productos de la categoria
	 */
	@OneToMany(
			mappedBy = "categoria",
			cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)
	private List<ProductoEntity> productos = new ArrayList<ProductoEntity>();

	/**
	 * Cotizaciones de la persona
	 */
	@OneToMany(
			mappedBy = "categoria",
			cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY,
			orphanRemoval = true
			)

	private List<CotizacionEntity> cotizaciones = new ArrayList<CotizacionEntity>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * @param icono the icono to set
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}

	/**
	 * @return the productos
	 */
	public List<ProductoEntity> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}

	/**
	 * @return the cotizaciones
	 */
	public List<CotizacionEntity> getCotizaciones() {
		return cotizaciones;
	}

	/**
	 * @param cotizaciones the cotizaciones to set
	 */
	public void setCotizaciones(List<CotizacionEntity> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}


}
