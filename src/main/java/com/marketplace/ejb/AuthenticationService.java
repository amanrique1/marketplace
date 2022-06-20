package com.marketplace.ejb;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.ConfigProvider;
import org.hibernate.service.spi.ServiceException;

import com.marketplace.DAO.UsuarioDAO;
import com.marketplace.model.Rol;
import com.marketplace.model.BodyParams.CrearUsuarioParams;
import com.marketplace.model.BodyParams.LoginParams;
import com.marketplace.model.ResponseBody.UsuarioInfo;
import com.marketplace.model.entity.AccessTokenEntity;
import com.marketplace.model.entity.PersonaEntity;
import com.marketplace.model.entity.ProveedorEntity;
import com.marketplace.model.entity.UsuarioEntity;
import com.marketplace.util.TokenUtils;

@ApplicationScoped
public class AuthenticationService {

	/**
	 * Objeto encargado de la persistencia
	 */
	@Inject
	UsuarioDAO usuarioDAO;

	/**
	 * Crea y guarda el access token para el usuario especificado
	 * @param loginParams email y contrasenia del usuario
	 * @return accessToken
	 * @throws ServiceException si no corresponde un usuario con esa contrasenia
	 */
	public UsuarioInfo iniciarSesion(LoginParams loginParams) throws ServiceException {
		UsuarioEntity usuario = usuarioDAO.buscarPersonaCorreoContrasenia(loginParams.getEmail(), loginParams.getContrasenia());
		if(usuario == null) {
			usuario = usuarioDAO.buscarProveedorCorreoContrasenia(loginParams.getEmail(), loginParams.getContrasenia());
			if(usuario == null) {
				String mensaje = ConfigProvider.getConfig().getValue("mensaje.login.incorrecto", String.class);
				throw new ServiceException(mensaje);
			}
		}
		try {
			String issuer = ConfigProvider.getConfig().getValue("mp.jwt.verify.issuer", String.class);
			Set<Rol> rolesSet = new HashSet<>();
			rolesSet.add(usuario.getRol());
			Integer duracion = ConfigProvider.getConfig().getValue("quarkusjwt.jwt.duration", Integer.class);
			String tokenStr = TokenUtils.generateToken(usuario.getEmail(), rolesSet,duracion,issuer);
			String refreshTokenStr = TokenUtils.generateToken(usuario.getEmail(), rolesSet,issuer);
			AccessTokenEntity token = usuarioDAO.buscarAccessTokenUsuario(loginParams.getEmail());
			if(token == null) {
				token = new AccessTokenEntity();
				token.setFechaHoraCreacion(LocalDateTime.now());
				token.setAccessToken(tokenStr);
				token.setUsuario(usuario);
				token.setRefreshToken(refreshTokenStr);
				usuarioDAO.agregarAccessToken(token);
			}else {
				token.setFechaHoraCreacion(LocalDateTime.now());
				token.setAccessToken(tokenStr);
				token.setUsuario(usuario);
				token.setRefreshToken(refreshTokenStr);
				usuarioDAO.actualizarAccessToken(token);
			}
			return new UsuarioInfo(token.getAccessToken(),token.getRefreshToken(),token.getFechaHoraCreacion(),usuario.getEmail(),usuario.getNombre(),usuario.getRol());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Crea y guarda el access token para el usuario especificado
	 * @param loginParams email y contrasenia del usuario
	 * @return accessToken
	 * @throws ServiceException si no corresponde un usuario con esa contrasenia
	 */
	public UsuarioInfo refrescarAccessToken(String refToken) throws ServiceException {
		try {
			String refreshToken = refToken.split(" ")[1];
			UsuarioEntity usuario = usuarioDAO.darUsuarioPorRefreshToken(refreshToken);
			if(usuario == null) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.refreshToken.inexistente", String.class));
			} else{
				AccessTokenEntity token = usuario.getAccesstoken();
				String issuer = ConfigProvider.getConfig().getValue("mp.jwt.verify.issuer", String.class);
				Set<Rol> rolesSet = new HashSet<>();
				rolesSet.add(usuario.getRol());
				Integer duracion = ConfigProvider.getConfig().getValue("quarkusjwt.jwt.duration", Integer.class);
				String tokenStr = TokenUtils.generateToken(usuario.getEmail(), rolesSet,duracion,issuer);
				String refreshTokenStr = TokenUtils.generateToken(usuario.getEmail(), rolesSet,issuer);
				token.setFechaHoraCreacion(LocalDateTime.now());
				token.setAccessToken(tokenStr);
				token.setRefreshToken(refreshTokenStr);
				usuarioDAO.actualizarAccessToken(token);
				return new UsuarioInfo(token.getAccessToken(),token.getRefreshToken(),token.getFechaHoraCreacion(),usuario.getEmail(),usuario.getNombre(),usuario.getRol());

			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Elimina el access token del usuario o proveedor
	 * @param email de la persona o proveedor
	 * @throws ServiceException si existe usuario con el correo o hay problemas eliminando el access token
	 */
	public void cerrarSesion(String email) throws ServiceException {
		UsuarioEntity usuario = usuarioDAO.buscarPersona(email);
		if(usuario == null) {
			usuario = usuarioDAO.buscarProveedor(email);
			if(usuario == null) {
				String mensaje = ConfigProvider.getConfig().getValue("mensaje.no.email", String.class);
				throw new ServiceException(mensaje);
			}
		}
		try {
			usuarioDAO.eliminarAccessToken(usuario);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Crea el usuario en la BD
	 * @param infoUsuario
	 */
	public void crearUsuario(CrearUsuarioParams infoUsuario) {
		Rol rol = Rol.valueOf(infoUsuario.getRol());
		if(infoUsuario.isPersona()) {
			PersonaEntity persona = new PersonaEntity();
			persona.setContrasenia(infoUsuario.getContrasenia());
			persona.setDocumento(infoUsuario.getDocumento());
			persona.setEmail(infoUsuario.getEmail());
			persona.setNombre(infoUsuario.getNombre());
			persona.setRol(rol);
			try {
				crearPersona(persona);
			}catch (ServiceException e) {
				throw new ServiceException(e.getMessage());
			}
		}else {
			ProveedorEntity proveedor = new ProveedorEntity();
			proveedor.setContrasenia(infoUsuario.getContrasenia());
			proveedor.setDocumento(infoUsuario.getDocumento());
			proveedor.setEmail(infoUsuario.getEmail());
			proveedor.setNombre(infoUsuario.getNombre());
			proveedor.setRol(rol);
			try {
				crearProveedor(proveedor);
			}catch (ServiceException e) {
				throw new ServiceException(e.getMessage());
			}
		}
	}

	/**
	 * Persiste la persona en la base de datos
	 * @param pPersona info de la persona
	 * @throws ServiceException si hay problemas agregando a la persona
	 */
	private void crearPersona(PersonaEntity pPersona) throws ServiceException {
		PersonaEntity persona =usuarioDAO.buscarPersona(pPersona.getEmail());
		if(persona==null) {
			try {
				usuarioDAO.agregarPersona(pPersona);
			}catch (Exception e) {
				throw new ServiceException(e.getMessage(),e);
			}
		}else {
			String mensaje = ConfigProvider.getConfig().getValue("mensaje.email.existente", String.class);
			throw new ServiceException(mensaje);
		}
	}

	/**
	 * Persiste el proveedor en la base de datos
	 * @param pProveedor info del proveedor
	 * @throws ServiceException si hay problemas agregando al proveedor
	 */
	private void crearProveedor(ProveedorEntity pProveedor) throws ServiceException {
		ProveedorEntity proveedor =usuarioDAO.buscarProveedor(pProveedor.getEmail());
		if(proveedor==null) {
			try {
				usuarioDAO.agregarProveedor(pProveedor);
			}catch (Exception e) {
				throw new ServiceException(e.getMessage(),e);
			}
		}else {
			String mensaje = ConfigProvider.getConfig().getValue("mensaje.email.existente", String.class);
			throw new ServiceException(mensaje);
		}
	}
}
