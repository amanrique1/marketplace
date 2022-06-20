package com.marketplace.model.ResponseBody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.Cotizacion;

public class ListaCotizaciones {
	
	/**
	 * Cotizaciones
	 */
	@JsonProperty("quotation")
	private List<Cotizacion> cotizaciones;
	
	/**
	 * Cotizaciones
	 * @param cotizaciones
	 */
	public ListaCotizaciones(List<Cotizacion> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

	/**
	 * @return the cotizaciones
	 */
	public List<Cotizacion> getCotizaciones() {
		return cotizaciones;
	}

	/**
	 * @param cotizaciones the cotizaciones to set
	 */
	public void setCotizaciones(List<Cotizacion> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}
}
