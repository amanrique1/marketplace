package com.marketplace.model.entity;

import java.io.Serializable;

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
@Table(name="Producto")
public class ProductoEntity implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -6601500133238519475L;

	/**
	 * Id Producto PK
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre producto
	 */
	@Column(nullable = false)
	private String nombre;

	/**
	 * Precio Total Prodcuto
	 */
	@Column(nullable = false)
	private Double precioTotal;

	/**
	 * Impuesto Producto
	 */
	@Column(nullable = false)
	private Double valorImpuesto;

	/**
	 * Fee cobrado al Producto
	 */
	private Double valorFee;

	/**
	 * Tipo de Moneda
	 */
	@Column(nullable = false)
	private String moneda;

	/**
	 * Marca producto
	 */
	@Column(nullable = false)
	private String marca;

	/**
	 * modelo producto
	 */
	@Column(nullable = false)
	private String modelo;

	/**
	 * Stock producto
	 */
	@Column(nullable = false)
	private Integer stock;

	/**
	 * Anchura del producto
	 */
	@Column(nullable = false)
	private Double anchura;

	/**
	 * Altura del producto
	 */
	@Column(nullable = false)
	private Double altura;

	/**
	 * Profundidad del producto
	 */
	@Column(nullable = false)
	private Double profundidad;

	/**
	 * Unidad de medida
	 */
	@Column(nullable = false)
	private String unidadDeMedida;

	/**
	 * peso del producto
	 */
	@Column(nullable = false)
	private Double peso;

	/**
	 * Unidad de peso
	 */
	@Column(nullable = false)
	private String unidadDePeso;

	/**
	 * Aprobacion de la publicacion del producto
	 */
	@Column(nullable = false)
	private Boolean aprobado;

	/**
	 * producto y producto orden
	 */
	@OneToOne(mappedBy = "producto",fetch = FetchType.LAZY)
	private ProductoOrdenEntity productoOrden;

	/**
	 * Productos de un  proveedor
	 */
	@ManyToOne
	@JoinColumn(nullable=false)
	private ProveedorEntity proveedor;

	/**
	 * Categoria de un producto
	 */
	@ManyToOne
	@JoinColumn(nullable=false)
	private CategoriaEntity categoria;

	/**
	 * Imagen del producto
	 */
	@OneToOne
	private ImagenEntity imagen;

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
	 * @return the precioTotal
	 */
	public Double getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	/**
	 * @return the valorImpuesto
	 */
	public Double getValorImpuesto() {
		return valorImpuesto;
	}

	/**
	 * @param valorImpuesto the valorImpuesto to set
	 */
	public void setValorImpuesto(Double valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	/**
	 * @return the valorFee
	 */
	public Double getValorFee() {
		return valorFee;
	}

	/**
	 * @param valorFee the valorFee to set
	 */
	public void setValorFee(Double valorFee) {
		this.valorFee = valorFee;
	}

	/**
	 * @return the moneda
	 * 
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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

	/**
	 * @return the categoria
	 */
	public CategoriaEntity getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * @return the anchura
	 */
	public Double getAnchura() {
		return anchura;
	}

	/**
	 * @param anchura the anchura to set
	 */
	public void setAnchura(Double anchura) {
		this.anchura = anchura;
	}

	/**
	 * @return the altura
	 */
	public Double getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(Double altura) {
		this.altura = altura;
	}

	/**
	 * @return the profundidad
	 */
	public Double getProfundidad() {
		return profundidad;
	}

	/**
	 * @param profundidad the profundidad to set
	 */
	public void setProfundidad(Double profundidad) {
		this.profundidad = profundidad;
	}

	/**
	 * @return the unidadDeMedida
	 */
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	/**
	 * @param unidadDeMedida the unidadDeMedida to set
	 */
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	/**
	 * @return the aprobado
	 */
	public Boolean getAprobado() {
		return aprobado;
	}

	/**
	 * @param aprobado the aprobado to set
	 */
	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}

	/**
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * @return the imagen
	 */
	public ImagenEntity getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(ImagenEntity imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the unidadDePeso
	 */
	public String getUnidadDePeso() {
		return unidadDePeso;
	}

	/**
	 * @param unidadDePeso the unidadDePeso to set
	 */
	public void setUnidadDePeso(String unidadDePeso) {
		this.unidadDePeso = unidadDePeso;
	}
}
