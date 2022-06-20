package com.marketplace.api;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import com.marketplace.ejb.FacturacionService;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.exceptions.WSException;
import com.marketplace.model.BodyParams.CambioEstadoParams;
import com.marketplace.model.BodyParams.CrearOrdenDeCompraParams;


@Singleton
@Authentication
@Tag(name="facturacion", description="Endpoints para funciones relacionadas a la venta de productos")
public class FacturacionResource {

	/**
	 * Objeto que se encarga de la logica
	 */
	@Inject
	FacturacionService facturacionService;

	/**
	 * JWT info decoder
	 */
	@Inject
	JsonWebToken jwt;

	/**
	 * Servicio para consultar nombre de productos
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException 
	 */
	@RolesAllowed({"SOLICITANTE","TESTER"})
	@POST
	@Path("/ordenDeCompra")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearOrdenDeCompra (@HeaderParam("Authorization") String token, CrearOrdenDeCompraParams ordenDeCompraParams) throws WSException {
		try {	
			return Response.status(Response.Status.OK).entity(facturacionService.crearOrdenDeCompra(ordenDeCompraParams,jwt.getSubject())).build();
		}catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST, e);
		}
	}

	/**
	 * Servicio para consultar nombre de productos
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException 
	 */
	@RolesAllowed({"SOLICITANTE","TESTER"})
	@GET
	@Path("/ordenDeCompra/{idOrden}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response darOrdenDeCompra (@HeaderParam("Authorization") String token, @PathParam("idOrden") Long idOrden) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(facturacionService.darOrdenDeCompra(idOrden)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_GATEWAY);
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
	@Path("/ordenesDeCompra/persona")
	@Produces(MediaType.APPLICATION_JSON)
	public Response darOrdenesDeCompraPersona(@HeaderParam("Authorization") String token,@QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		try {
			if(limite==null) {
				throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
			}
			if(posInicial==null) {
				posInicial = 0;
			}
			return Response.status(Response.Status.OK).entity(facturacionService.darOrdenesDeCompraPersona(jwt.getSubject(),posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * Servicio para consultar nombre de productos
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException 
	 */
	@RolesAllowed({"SOLICITANTE","TESTER"})
	@GET
	@Path("/ordenesDeCompra/{estado}/persona")
	@Produces(MediaType.APPLICATION_JSON)
	public Response darOrdenesDeCompraPersonaEstado(@HeaderParam("Authorization") String token,@PathParam("estado") String estado,@QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		try {
			if(limite==null) {
				throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
			}
			if(posInicial==null) {
				posInicial = 0;
			}
			return Response.status(Response.Status.OK).entity(facturacionService.darOrdenesDeCompraPersonaEstado(jwt.getSubject(),estado,posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * Servicio para consultar nombre de productos
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException 
	 */
	@RolesAllowed({"SOLICITANTE","TESTER"})
	@GET
	@Path("/ordenesDeCompra/{estado}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response darOrdenesDeCompraEstado(@HeaderParam("Authorization") String token,@PathParam("estado") String estado,@QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		try {
			if(limite==null) {
				throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
			}
			if(posInicial==null) {
				posInicial = 0;
			}
			return Response.status(Response.Status.OK).entity(facturacionService.darOrdenesDeCompraEstado(estado,posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST);
		}
	}

	/**
	 * Servicio para consultar nombre de productos
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException 
	 */
	@RolesAllowed({"ADMIN","TESTER"})
	@PUT
	@Path("/ordenDeCompra/{idOrden}/cambiarEstado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cambiarEstadoOrdenDeCompra (@HeaderParam("Authorization") String token,  @PathParam("idOrden") Long idOrden,CambioEstadoParams nuevoEstado) throws WSException {
		try {
			facturacionService.cambiarEstadoOrdenCompra(nuevoEstado,idOrden,jwt.getSubject());
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_GATEWAY);
		}
	}
}