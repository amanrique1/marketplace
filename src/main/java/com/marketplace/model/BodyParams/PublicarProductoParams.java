package com.marketplace.model.BodyParams;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicarProductoParams {

	/**
	 * Comision por la venta del producto
	 */
	@JsonProperty("feeValue")
	private Double valorFee;

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
}