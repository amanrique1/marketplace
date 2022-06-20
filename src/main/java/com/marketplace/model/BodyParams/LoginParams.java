package com.marketplace.model.BodyParams;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginParams {

	/**
	 * Email del usuario
	 */
	private String email;

	/**
	 * Contrasenia del usuario
	 */
	@JsonProperty("password")
	private String contrasenia;

	/**
	 * Constructor
	 * @param email
	 * @param contrasenia
	 */
	public LoginParams(String email, String contrasenia) {
		this.email = email;
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
