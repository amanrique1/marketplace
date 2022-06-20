package com.marketplace.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.marketplace.model.Rol;

@Entity
@Table(name="Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioEntity  implements  Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7790750503810052116L;

	/**
	 * Email del Usuario pk
	 */
	@Id
	private String email;

	/**
	 * Nombre del Usuario
	 */
	@Column(nullable = false)
	private String nombre;

	/**
	 * Rol del Usuario
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Rol rol;

	/**
	 * Documento de Identifiacion del Usuario
	 */
	@Column(nullable = false)
	private String documento;

	/**
	 * Contraseña del Usuario
	 */
	@Column(nullable = false)
	private String contrasenia;

	/**
	 * Access Token del Usuario
	 */
	@OneToOne(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private AccessTokenEntity accessToken;

	/**
	 * Identificador de si es un usuario valido
	 */
	private Boolean bloqueado;
	
	/**
	 * persona y sus pqs
	 */
	@OneToMany(mappedBy = "usuario")
	private  List<PQRSEntity> pqs = new ArrayList<PQRSEntity>();
	
	/**
	 * Cambios de estado para una orden de compra
	 */
	@OneToMany(mappedBy = "usuarioModificador",fetch = FetchType.LAZY)
	private List<CambioEstadoOrdenDeCompraEntity> cambiosEstadoOC;
	
	/**
	 * Access Token del Usuario
	 */
	@OneToMany(mappedBy = "usuarioModificador",fetch = FetchType.LAZY)
	private List<CambioEstadoPQRSEntity> cambiosEstadoPQS;

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
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(Rol rol) {
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
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the accesstoken
	 */
	public AccessTokenEntity getAccesstoken() {
		return accessToken;
	}

	/**
	 * @param accesstoken the accesstoken to set
	 */
	public void setAccesstoken(AccessTokenEntity accesstoken) {
		this.accessToken = accesstoken;
	}

	/**
	 * @return the bloqueado
	 */
	public Boolean isBloqueado() {
		return bloqueado;
	}

	/**
	 * @param bloqueado the bloqueado to set
	 */
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	/**
	 * @return the pqs
	 */
	public List<PQRSEntity> getPqs() {
		return pqs;
	}

	/**
	 * @param pqs the pqs to set
	 */
	public void setPqs(List<PQRSEntity> pqs) {
		this.pqs = pqs;
	}

	/**
	 * @return the bloqueado
	 */
	public Boolean getBloqueado() {
		return bloqueado;
	}

	/**
	 * @return the cambiosEstadoOC
	 */
	public List<CambioEstadoOrdenDeCompraEntity> getCambiosEstadoOC() {
		return cambiosEstadoOC;
	}

	/**
	 * @param cambiosEstadoOC the cambiosEstadoOC to set
	 */
	public void setCambiosEstadoOC(List<CambioEstadoOrdenDeCompraEntity> cambiosEstadoOC) {
		this.cambiosEstadoOC = cambiosEstadoOC;
	}

	/**
	 * @return the cambiosEstadoPQS
	 */
	public List<CambioEstadoPQRSEntity> getCambiosEstadoPQS() {
		return cambiosEstadoPQS;
	}

	/**
	 * @param cambiosEstadoPQS the cambiosEstadoPQS to set
	 */
	public void setCambiosEstadoPQS(List<CambioEstadoPQRSEntity> cambiosEstadoPQS) {
		this.cambiosEstadoPQS = cambiosEstadoPQS;
	}

}
