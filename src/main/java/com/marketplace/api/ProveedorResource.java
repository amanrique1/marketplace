package com.marketplace.api;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import com.marketplace.ejb.ProveedorService;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.exceptions.WSException;

@Singleton
@Authentication
@Tag(name="proveedor", description="Endpoints para funciones relacionadas con el proveedor")
public class ProveedorResource {

	/**
	 * Objeto que se encarga de la logica
	 */
	@Inject
	ProveedorService proveedorService;

	/**
	 * JWT info decoder
	 */
	@Inject
	JsonWebToken jwt;

	/**
	 * Servicio para consultar las cotizaciones pertenecientes a un proveedor
	 * @param HeaderParam accesToken
	 * @param QueryParam posInicial de busqueda
	 * @param QueryParam limite de busqueda
	 * @return cotizaciones
	 * @throws WSException si no se envia el limite de registros
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/cotizaciones")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  darCotizaciones (@HeaderParam("Authorization") String token, @QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		if(limite==null) {
			throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
		}
		if(posInicial==null) {
			posInicial = 0;
		}
		return Response.status(Response.Status.OK).entity(proveedorService.darCotizacionesProveedor(jwt.getSubject(),posInicial,limite)).build();
	}

	/**
	 * Servicio para consultar los productos pertenecientes a un proveedor
	 * @param HeaderParam accesToken
	 * @param QueryParam posInicial de busqueda
	 * @param QueryParam limite de busqueda
	 * @return cotizaciones
	 * @throws WSException si no se envia el limite de registros
	 */
	@RolesAllowed({"GERENTE","PROVEEDOR","TESTER"})
	@GET
	@Path("/productos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response darProductos(@HeaderParam("Authorization") String token, @QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		if(limite==null) {
			throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
		}
		if(posInicial==null) {
			posInicial = 0;
		}
		try {
			return Response.status(Response.Status.OK).entity(proveedorService.darProductosProveedor(jwt.getSubject(),posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST,e);
		}
	}
	
	/**
	 * Servicio para consultar las ordenes de compra de una persona
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException 
	 */
	@RolesAllowed({"SOLICITANTE","TESTER"})
	@GET
	@Path("/ordenesDeCompra")
	@Produces(MediaType.APPLICATION_JSON)
	public Response darOrdenesDeCompraProveedor(@HeaderParam("Authorization") String token,@QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		try {
			if(limite==null) {
				throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
			}
			if(posInicial==null) {
				posInicial = 0;
			}
			return Response.status(Response.Status.OK).entity(proveedorService.darOrdenesDeCompraProveedor(jwt.getSubject(),posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST);
		}
	}
}
