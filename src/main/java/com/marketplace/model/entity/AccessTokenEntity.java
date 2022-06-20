package com.marketplace.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="AccessToken")
public class AccessTokenEntity implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 3048292808757364216L;

	/**
	 * Usuario duenio del token
	 */
	@Id
	@MapsId
	@OneToOne
	@JoinColumn(name = "usuario", referencedColumnName = "email",nullable = false)
	private UsuarioEntity usuario;

	/**
	 * Access Token
	 */
	@Column(columnDefinition = "TEXT", nullable = false)
	private String accessToken;
	
	/**
	 * Token para actualizar el access token
	 */
	@Column(columnDefinition = "TEXT", nullable = false)
	private String refreshToken;

	/**
	 * Fecha Creacion del la Cuenta
	 */
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaHoraCreacion;

	/**
	 * @return the usuario
	 */
	public UsuarioEntity getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the accesToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accesToken the accesToken to set
	 */
	public void setAccessToken(String accesToken) {
		this.accessToken = accesToken;
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
	 * @return the fechaHoraCreacion
	 */
	public LocalDateTime getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}

	/**
	 * @param fechaHoraCreacion the fechaHoraCreacion to set
	 */
	public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

}
