package com.marketplace.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Producto {

	/**
	 * Id Producto PK
	 */
	private Long id;

	/**
	 * Nombre producto
	 */
	@JsonProperty("name")
	private String nombre;

	/**
	 * Precio Total Prodcuto
	 */
	@JsonProperty("finalPrice")
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
	 * Medidas del producto
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
	 * Imagen del producto
	 */
	@JsonProperty("image")
	private Imagen imagen;
	
	/**
	 * Aprobacion del producto
	 */
	private Boolean aprobado;

	/**
	 * Constructor
	 * @param id del producto
	 * @param nombre del producto
	 * @param precioTotal del producto
	 * @param valorImpuesto del producto
	 * @param moneda del producto
	 * @param stock del producto
	 * @param marca del producto
	 * @param modelo del producto
	 * @param anchura del producto
	 * @param altura del producto
	 * @param profundidad del producto
	 * @param unidadDeMedida del producto
	 * @param peso del producto
	 * @param unidadDePeso
	 * @param imagen del producto
	 * @param formatoImagen del producto
	 */
	public Producto(Long id,String nombre,Double precioTotal,Double valorImpuesto,String moneda,Integer stock,String marca,String modelo,Double peso,String unidadDePeso,Double anchura,Double altura,Double profundidad,String unidadDeMedida,String imagen,FormatoImagen formatoImagen) {
		this.id = id;
		this.peso = peso;
		this.marca = marca;
		this.modelo = modelo;
		this.moneda = moneda;
		this.nombre = nombre;
		this.peso = peso;
		this.precioTotal = precioTotal;
		this.stock = stock;
		this.unidadDePeso = unidadDePeso;
		this.valorImpuesto = valorImpuesto;
		this.imagen = new Imagen(imagen,formatoImagen.toString());
		this.medida = new Medidas();
		this.medida.setAnchura(anchura);
		this.medida.setAltura(altura);
		this.medida.setProfundidad(profundidad);
		this.medida.setUnidadDeMedida(unidadDeMedida);
	}
	
	/**
	 * Constructor
	 * @param id del producto
	 * @param nombre del producto
	 * @param precioTotal del producto
	 * @param valorImpuesto del producto
	 * @param moneda del producto
	 * @param stock del producto
	 * @param marca del producto
	 * @param modelo del producto
	 * @param anchura del producto
	 * @param altura del producto
	 * @param profundidad del producto
	 * @param unidadDeMedida del producto
	 * @param peso del producto
	 * @param unidadDePeso
	 * @param aprobado para saber si el producto ya esta listado
	 * @param imagen del producto
	 * @param formatoImagen del producto
	 */
	public Producto(Long id,String nombre,Double precioTotal,Double valorImpuesto,String moneda,Integer stock,String marca,String modelo,Double peso,String unidadDePeso,Double anchura,Double altura,Double profundidad,String unidadDeMedida,Boolean aprobado,String imagen,FormatoImagen formatoImagen) {
		this.id = id;
		this.peso = peso;
		this.marca = marca;
		this.modelo = modelo;
		this.moneda = moneda;
		this.nombre = nombre;
		this.peso = peso;
		this.precioTotal = precioTotal;
		this.stock = stock;
		this.unidadDePeso = unidadDePeso;
		this.valorImpuesto = valorImpuesto;
		this.imagen = new Imagen(imagen,formatoImagen.toString());
		this.medida = new Medidas();
		this.medida.setAnchura(anchura);
		this.medida.setAltura(altura);
		this.medida.setProfundidad(profundidad);
		this.medida.setUnidadDeMedida(unidadDeMedida);
	}

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
	 * @return the imagen
	 */
	public Imagen getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
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

}
