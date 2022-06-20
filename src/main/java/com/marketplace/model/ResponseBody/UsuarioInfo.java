package com.marketplace.model.ResponseBody;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketplace.model.Rol;

public class UsuarioInfo {

	/**
	 * Email del Usuario pk
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
	private Rol rol;

	/**
	 * Valor del token
	 */
	private String accessToken;
	
	/**
	 * Valor del refrech token
	 */
	private String refreshToken;

	/**
	 * Fecha Creacion del access token
	 */
	@JsonProperty("creationDateTime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaHoraCreacion;

	/**
	 * Constructo
	 * @param accessToken de ingreso
	 * @param fechaHoraCreacion fecha y hora creacion del access token
	 * @param email
	 * @param nombre
	 * @param rol
	 */
	public UsuarioInfo(String accessToken,String refreshToken, LocalDateTime fechaHoraCreacion, String email, String nombre, Rol rol) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.fechaHoraCreacion = fechaHoraCreacion;
		this.email = email;
		this.nombre = nombre;
		this.rol = rol;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the creationDateTime
	 */
	public LocalDateTime getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}

	/**
	 * @param creationDateTime the creationDateTime to set
	 */
	public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
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
	 * @return the name
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param name the name to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the role
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol the role to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}