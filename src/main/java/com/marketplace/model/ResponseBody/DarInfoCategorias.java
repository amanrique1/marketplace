package com.marketplace.model.ResponseBody;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.Categoria;

public class DarInfoCategorias {

	/**
	 * Lista de categorias
	 */
	@JsonProperty("categories")
	private List<Categoria> categorias;

	/**
	 * @return the categorias
	 */
	public List<Categoria> getcategorias() {
		return categorias;
	}

	/**
	 * @param categorias the categorias to set
	 */
	public void setcategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}