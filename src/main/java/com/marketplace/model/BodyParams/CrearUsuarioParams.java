package com.marketplace.model.BodyParams;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrearUsuarioParams {
	
	/**
	 * Indicador boolean para crear persona o proveedor
	 */
	@JsonProperty("person")
	private boolean persona;

	/**
	 * Email del Usuario
	 */
	private String email;

	/**
	 * Nombre del Usuario
	 */
	@JsonProperty("name")
	private String nombre;

	/**
	 * Rol del Usuario
	 */
	@JsonProperty("role")
	private String rol;

	/**
	 * Documento de Identifiacion del Usuario
	 */
	@JsonProperty("document")
	private String documento;

	/**
	 * Contraseña del Usuario
	 */
	@JsonProperty("password")
	private String contrasenia;

	/**
	 * @return the persona
	 */
	public boolean isPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(boolean persona) {
		this.persona = persona;
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
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
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
	 * @return the contraseña
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contraseña the contraseña to set
	 */
	public void setContraseña(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
