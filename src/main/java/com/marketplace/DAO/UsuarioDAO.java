package com.marketplace.DAO;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.marketplace.exceptions.DAOException;
import com.marketplace.model.entity.AccessTokenEntity;
import com.marketplace.model.entity.PersonaEntity;
import com.marketplace.model.entity.ProveedorEntity;
import com.marketplace.model.entity.UsuarioEntity;

@RequestScoped
public class UsuarioDAO {

	/**
	 * Manejador de la persistencia
	 */
	@Inject
	EntityManager em;

	/**
	 * Busca el objeto persona a partir del correo y contrasenia
	 * @param correo del usuario
	 * @param contrasenia del usuario
	 * @return persona
	 */
	public PersonaEntity buscarPersonaCorreoContrasenia(String correo, String contrasenia){
		PersonaEntity persona = em.find(PersonaEntity.class, correo);
		if(persona != null) {
			if (persona.getContrasenia().equals(contrasenia)) {
				return persona;
			}
		}
		return null;
	}

	/**
	 * Busca el objeto proveedor a partir del correo y contrasenia
	 * @param correo del usuario
	 * @param contrasenia del usuario
	 * @return proveedor
	 */
	public ProveedorEntity buscarProveedorCorreoContrasenia(String correo, String contrasenia){
		ProveedorEntity proveedor = em.find(ProveedorEntity.class, correo);
		if(proveedor != null) {
			if (proveedor.getContrasenia().equals(contrasenia)) {
				return proveedor;
			}
		}
		return null;
	}

	/**
	 * Busca la persona a traves de su correo
	 * @param correo del usuario
	 * @return persona
	 */
	public PersonaEntity buscarPersona(String correo){
		return em.find(PersonaEntity.class, correo);
	}
	
	/**
	 * Busca el usuario a traves de su correo
	 * @param correo del usuario
	 * @return persona
	 */
	public UsuarioEntity buscarUsuario(String correo){
		return em.find(UsuarioEntity.class, correo);
	}

	/**
	 * Revisa si existe una persona a partir de su correo
	 * @param correo del usuario
	 * @return boolean que identifica si existe o no
	 */
	public Boolean existePersona(String correo){
		Query query = em.createQuery("SELECT p.nombre FROM PersonaEntity p WHERE p.email=:email");
		query.setParameter("email", correo);
		return !query.getResultList().isEmpty() ;
	}
	
	/**
	 * Revisa si existe un proveedor a partir de su correo
	 * @param correo del usuario
	 * @return boolean que identifica si existe o no
	 */
	public Boolean existeProveedor(String correo){
		Query query = em.createQuery("SELECT p.nombre FROM ProveedorEntity p WHERE p.email=:email");
		query.setParameter("email", correo);
		return !query.getResultList().isEmpty();
	}

	/**
	 * Busca el proveedor a traves de su correo
	 * @param correo del proveedor
	 * @return proveedor
	 */
	public ProveedorEntity buscarProveedor(String correo){
		return em.find(ProveedorEntity.class, correo);
	}

	/**
	 * Busca el access token de un usuario
	 * @param email del duenio del token
	 * @return accessToken
	 */
	public AccessTokenEntity buscarAccessTokenUsuario(String email){
		return em.find(AccessTokenEntity.class, email);
	}

	/**
	 * Agrega una persona a la base de datos
	 * @param persona a persistir
	 * @throws Exception si hay un error guardando a la persona 
	 * ej: ya existe el email
	 */
	@Transactional
	public void agregarPersona(PersonaEntity persona) throws DAOException {
		try {
			em.persist(persona);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Agrega un proveedor a la base de datos
	 * @param proveedor a persistir
	 * @throws exception si hay un error guardando al proveedor 
	 * ej: ya existe el email
	 */
	@Transactional
	public void agregarProveedor(ProveedorEntity proveedor) throws DAOException{
		try {
			em.persist(proveedor);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Agrega el access token y actualiza la informacion del usuario para que en la Bd apunte al accesstoken
	 * @param usuario a actualizar con el access token a guardar
	 * @throws Exception si hay algun problema guardando el access token
	 * @throws Exception si hay algun problema actualizando el usuario
	 */
	@Transactional
	public void agregarAccessToken(AccessTokenEntity token) throws DAOException{
		try {
			em.persist(token);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Busca un accesstoken a partir de su codigo
	 * @param accessToken code
	 * @return AccessTokenEntity object
	 */
	public AccessTokenEntity buscarAccessToken(String accessToken) {
		String queryStr =
				"SELECT a FROM AccessTokenEntity AS a WHERE a.accessToken=:accessToken";
		TypedQuery<AccessTokenEntity> query = em.createQuery(queryStr,AccessTokenEntity.class);
		query.setParameter("accessToken", accessToken);
		List<AccessTokenEntity> list = query.getResultList();
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * Busca un accesstoken a partir de su refresh token
	 * @param accessToken code
	 * @return AccessTokenEntity object
	 */
	public AccessTokenEntity darRefreshToken(String refreshToken) {
		String queryStr =
				"SELECT a FROM AccessTokenEntity a WHERE a.refreshToken=:refreshToken";
		TypedQuery<AccessTokenEntity> query = em.createQuery(queryStr,AccessTokenEntity.class);
		query.setParameter("refreshToken", refreshToken);
		List<AccessTokenEntity> list = query.getResultList();
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * Busca un usuario a partir de su refresh token
	 * @param refreshToken code
	 * @return Usuario object
	 */
	public UsuarioEntity darUsuarioPorRefreshToken(String refreshToken) {
		String queryStr =
				"SELECT u FROM AccessTokenEntity a JOIN a.usuario u WHERE a.refreshToken=:refreshToken";
		TypedQuery<UsuarioEntity> query = em.createQuery(queryStr,UsuarioEntity.class);
		query.setParameter("refreshToken", refreshToken);
		List<UsuarioEntity> list = query.getResultList();
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * Actualiza la info del accesstoken
	 * @param token a actualizar
	 * @throws DAOException si hay un error actualizando la informacion del accesstoken
	 */
	@Transactional
	public void actualizarAccessToken(AccessTokenEntity token) throws DAOException{
		try {
			em.merge(token);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Actualiza la informacion del cliente
	 * @param usuario a actualizar
	 * @throws DAOException si hay un error actualizando el cliente
	 */
	@Transactional
	public void actualizarCliente(UsuarioEntity usuario) throws DAOException{
		try {
			em.merge(usuario);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Elimina el access token y actualiza la informacion del usuario para que en la Bd no haya accesstoken
	 * @param usuario a actualizar con el access token a eliminar
	 * @throws Exception si hay algun problema eliminando el access token
	 * @throws Exception si hay algun problema actualizando el usuario
	 */
	@Transactional
	public void eliminarAccessToken(UsuarioEntity usuario) throws DAOException{
		AccessTokenEntity acc = usuario.getAccesstoken();
		if(acc != null) {
			try {
				AccessTokenEntity accessToken = em.find(AccessTokenEntity.class, acc.getAccessToken());
				usuario.setAccesstoken(null);
				em.merge(usuario);
				em.remove(accessToken);
			}catch (Exception e) {
				throw new DAOException(e.getMessage(),e);
			}
		}
	}

}
