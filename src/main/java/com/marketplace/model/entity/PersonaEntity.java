package com.marketplace.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="Persona")
public class PersonaEntity extends UsuarioEntity  {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1482955814691409162L;

	/**
	 * persona y sus requerimientos
	 */
	@OneToMany(mappedBy = "persona")
	private List<CotizacionEntity>  requerimientos = new ArrayList<CotizacionEntity>();

	/**
	 * persona y sus ordenes de compra
	 */
	@OneToMany(mappedBy = "persona",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private List<OrdenDeCompraEntity>  ordendecompra = new ArrayList<OrdenDeCompraEntity>();

	/**
	 * Carrito tiene muchos productos
	 */
	@OneToMany(mappedBy = "persona",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<ProductoCarrito>  productosCarrito = new ArrayList<ProductoCarrito>();

	/**
	 * @return the requerimientos
	 */
	public List<CotizacionEntity> getRequerimientos() {
		return requerimientos;
	}

	/**
	 * @param requerimientos the requerimientos to set
	 */
	public void setRequerimientos(List<CotizacionEntity> requerimientos) {
		this.requerimientos = requerimientos;
	}

	/**
	 * @return the ordendecompra
	 */
	public List<OrdenDeCompraEntity> getOrdendecompra() {
		return ordendecompra;
	}

	/**
	 * @param ordendecompra the ordendecompra to set
	 */
	public void setOrdendecompra(List<OrdenDeCompraEntity> ordendecompra) {
		this.ordendecompra = ordendecompra;
	}

	/**
	 * @return the productosCarrito
	 */
	public List<ProductoCarrito> getProductosCarrito() {
		return productosCarrito;
	}

	/**
	 * @param productosCarrito the productosCarrito to set
	 */
	public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
		this.productosCarrito = productosCarrito;
	}

}
