package com.marketplace.model.BodyParams;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.Imagen;
import com.marketplace.model.Medidas;

public class AgregarProductoParams {

	/**
	 * Nombre producto
	 */
	@JsonProperty("name")
	private String nombre;

	/**
	 * Precio Total Prodcuto
	 */
	@JsonProperty("totalPrice")
	private Double precioTotal;

	/**
	 * Impuesto Producto
	 */
	@JsonProperty("taxValue")
	private Double valorImpuesto;

	/**
	 * Tipo de Moneda
	 */
	@JsonProperty("currency")
	private String moneda;

	/**
	 * Marca producto
	 */
	@JsonProperty("brand")
	private String marca;

	/**
	 * modelo producto
	 */
	@JsonProperty("model")
	private String modelo;

	/**
	 * Stock producto
	 */
	private Integer stock;

	/**
	 * medida producto
	 */
	@JsonProperty("measures")
	private Medidas medida;

	/**
	 * peso del producto
	 */
	@JsonProperty("weight")
	private Double peso;

	/**
	 * Unidad de peso
	 */
	@JsonProperty("weightUnit")
	private String unidadDePeso;

	/**
	 * Categoria de un producto
	 */
	@JsonProperty("category")
	private Long categoria;

	/**
	 * Imagen del producto
	 */
	@JsonProperty("image")
	private Optional<Imagen> imagen;

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
	 * @return the moneda
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
	 * @return the medida
	 */
	public Medidas getMedida() {
		return medida;
	}

	/**
	 * @param medida the medida to set
	 */
	public void setMedida(Medidas medida) {
		this.medida = medida;
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

	/**
	 * @return the categoria
	 */
	public Long getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the imagen
	 */
	public Optional<Imagen> getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(Optional<Imagen> imagen) {
		this.imagen = imagen;
	}
}
