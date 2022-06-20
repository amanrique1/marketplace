package com.marketplace.api;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.hibernate.service.spi.ServiceException;

import com.marketplace.ejb.AuthenticationService;
import com.marketplace.exceptions.WSException;
import com.marketplace.model.BodyParams.CrearUsuarioParams;
import com.marketplace.model.BodyParams.LoginParams;

@Singleton
@Tag(name="auth", description="Endpoints relacionados a autenticacion")
public class AuthenticationResource {

	/**
	 * Objeto que se encarga de la logica
	 */
	@Inject
	AuthenticationService auth;
	
	/**
	 * JWT info decoder
	 */
	@Inject
	JsonWebToken jwt;

	/**
	 * Servicio para iniciar sesion y recibir access token
	 * @param loginParams email y contrasenia
	 * @return access token
	 * @throws WSException si no se encuentra el usuario con la contrasenia
	 */
	@PermitAll
	@POST 
	@Path("/iniciarSesion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public Response iniciarSesion(LoginParams loginParams) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(auth.iniciarSesion(loginParams)).build();
		}catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST, e);
		}
	}
	
	/**
	 * Servicio para iniciar sesion y recibir access token
	 * @return access token
	 * @throws WSException si no se encuentra el usuario o el refresh token no corresponde
	 */
	@GET 
	@Path("/refrescarSesion")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response refrescarAccessToken(@HeaderParam("Authorization") String refToken) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(auth.refrescarAccessToken(refToken)).build();
		}catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST, e);
		}
	}

	/**
	 * Elimina el access token de tal forma que este no sirva mas
	 * @param email del usuario
	 * @return codigo respuesta 204 si todo salio bien
	 * @throws WSException si hay problemas eliminando el access token
	 */
	@PermitAll
	@DELETE 
	@Path("/cerrarSesion/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cerrarSesion(@PathParam("email") String email) throws WSException {
		try {
			auth.cerrarSesion(email);
		}catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST, e);
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	/**
	 * Persiste la info deo usuario en la base de datos
	 * @param params info del usuario a crear
	 * @return codigo respuesta 204 si todo salio bien
	 * @throws WSException si hay problemas creando el usuario
	 * ej: email ya registrado
	 */
	@Authentication
	@RolesAllowed("ADMIN")
	@POST
	@Path("/registrarse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearUsuario(@HeaderParam("Authorization") String accessToken, CrearUsuarioParams params) throws WSException {
		try {
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			if(!params.getEmail().matches(regex)) {
				throw new WSException(ConfigProvider.getConfig().getValue("mensaje.email.invalido", String.class), Response.Status.BAD_REQUEST);
			}
			auth.crearUsuario(params);
		}catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST, e);
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
