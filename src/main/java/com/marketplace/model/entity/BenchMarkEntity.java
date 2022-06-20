package com.marketplace.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BenchMark")
public class BenchMarkEntity implements  Serializable  {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 4104700364693013017L;

	/**
	 * Id del BenchMark pk
	 */
	@Id
	private String id;

	/**
	 * Benchmark de un proveedor
	 */
	@OneToOne
	@JoinColumn(name = "proveedor", referencedColumnName = "email",nullable = false)
	private ProveedorEntity proveedor;

	/**
	 * Benchmark de un Producctoorden
	 */
	@OneToOne
	@JoinColumn(nullable = false)
	private ProductoOrdenEntity productoOrden;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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

	/**
	 * @return the productoOrden
	 */
	public ProductoOrdenEntity getProductoOrden() {
		return productoOrden;
	}

	/**
	 * @param productoOrden the productoOrden to set
	 */
	public void setProductoOrden(ProductoOrdenEntity productoOrden) {
		this.productoOrden = productoOrden;
	}

}
