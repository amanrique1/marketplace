package com.marketplace.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductoOrden")
public class ProductoOrdenEntity implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 4092110537488325247L;

	/**
	 * Id ProductoOrden PK
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Cantidad de Prducto
	 */
	@Column(nullable = false)
	private Integer cantidadProducto;

	/**
	 * Detalles del producto
	 */
	private String detalles;

	/**
	 * Valor total de la orden
	 */
	@JoinColumn(nullable = false)
	private Double valorTotal;

	/**
	 * Unidad monetaria
	 */
	@JoinColumn(nullable = false)
	private String unidadMonetaria;

	/**
	 *  Producto de la orden
	 */
	@OneToOne
	@JoinColumn(nullable = false)
	private ProductoEntity producto;

	/**
	 * Reclamo sobre ese producto
	 */
	@OneToOne(mappedBy = "productoOrden",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private PQRSEntity reclamo;

	/**
	 * benchmark de la compra
	 */
	@OneToOne(mappedBy = "productoOrden",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private BenchMarkEntity benchmark;

	/**
	 * prodcutos de una orden de compra
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
	 * @return the cantidadProducto
	 */
	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	/**
	 * @param cantidadProducto the cantidadProducto to set
	 */
	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
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
	 * @return the producto
	 */
	public ProductoEntity getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	/**
	 * @return the reclamo
	 */
	public PQRSEntity getReclamo() {
		return reclamo;
	}

	/**
	 * @param reclamo the reclamo to set
	 */
	public void setReclamo(PQRSEntity reclamo) {
		this.reclamo = reclamo;
	}

	/**
	 * @return the benchmark
	 */
	public BenchMarkEntity getBenchmark() {
		return benchmark;
	}

	/**
	 * @param benchmark the benchmark to set
	 */
	public void setBenchmark(BenchMarkEntity benchmark) {
		this.benchmark = benchmark;
	}

	/**
	 * @return the ordendecompra
	 */
	public OrdenDeCompraEntity getOrdendecompra() {
		return ordenDeCompra;
	}

	/**
	 * @param ordendecompra the ordendecompra to set
	 */
	public void setOrdendecompra(OrdenDeCompraEntity ordendecompra) {
		this.ordenDeCompra = ordendecompra;
	}

	/**
	 * @return the valorTotal
	 */
	public Double getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the unidadMonetaria
	 */
	public String getUnidadMonetaria() {
		return unidadMonetaria;
	}

	/**
	 * @param unidadMonetaria the unidadMonetaria to set
	 */
	public void setUnidadMonetaria(String unidadMonetaria) {
		this.unidadMonetaria = unidadMonetaria;
	}
}
