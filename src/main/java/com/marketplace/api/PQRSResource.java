package com.marketplace.api;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.marketplace.ejb.PQRSService;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.exceptions.WSException;
import com.marketplace.model.BodyParams.CrearPQRSParams;

@Singleton
@Authentication
@Tag(name="PQRS", description="Endpoints para funciones relacionadas a peticiones, quejas, reclamos y sugerencias")
public class PQRSResource {

	/**
	 * Objeto que se encarga de la logica
	 */
	@Inject
	PQRSService pqrsService;

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
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearPQS (@HeaderParam("Authorization") String token,CrearPQRSParams pqsParams) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(pqrsService.crearPQS(pqsParams, jwt.getSubject())).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST,e);
		}
	}

}
