package com.marketplace.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Proveedor")
public class ProveedorEntity extends UsuarioEntity  {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -5958864207034320349L;

	/**
	 * Calificaiones proveedor
	 */
	@OneToMany(mappedBy = "proveedor")
	private List<CalificacionEntity> calificacion = new ArrayList<CalificacionEntity>();

	/**
	 * Requerimientos de un proveedor
	 */
	@OneToOne(mappedBy = "proveedor",cascade = CascadeType.PERSIST)
	private CotizacionEntity requerimiento;

	/**
	 *prodcutos de un proveedor
	 */
	@OneToMany(mappedBy = "proveedor")
	private List<ProductoEntity>  producto = new ArrayList<ProductoEntity>();

	/**
	 * benchmark de un proveedor
	 */
	@OneToOne(mappedBy ="proveedor",fetch = FetchType.LAZY)
	private BenchMarkEntity benchmark  ;

	/**
	 * @return the calificacion
	 */
	public List<CalificacionEntity> getCalificacion() {
		return calificacion;
	}

	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(List<CalificacionEntity> calificacion) {
		this.calificacion = calificacion;
	}

	/**
	 * @return the requerimiento
	 */
	public CotizacionEntity getRequerimiento() {
		return requerimiento;
	}

	/**
	 * @param requerimiento the requerimiento to set
	 */
	public void setRequerimiento(CotizacionEntity requerimiento) {
		this.requerimiento = requerimiento;
	}

	/**
	 * @return the producto
	 */
	public List<ProductoEntity> getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(List<ProductoEntity> producto) {
		this.producto = producto;
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

}
