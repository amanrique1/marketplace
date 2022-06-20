package com.marketplace.api;

import javax.annotation.security.PermitAll;
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

import com.marketplace.ejb.InventarioService;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.exceptions.WSException;
import com.marketplace.model.BodyParams.AgregarCategoriaParams;
import com.marketplace.model.BodyParams.AgregarCotizacionParams;
import com.marketplace.model.BodyParams.AgregarProductoParams;
import com.marketplace.model.BodyParams.EmailParam;
import com.marketplace.model.BodyParams.PublicarProductoParams;

@Singleton
@Authentication
@Tag(name="inventario", description="Endpoints para funciones relacionadas con invetario y producto")
public class InventarioResource {

	/**
	 * Objeto que se encarga de la logica
	 */
	@Inject
	InventarioService inventarioService;

	/**
	 * JWT info decoder
	 */
	@Inject
	JsonWebToken jwt;

	/**
	 * Servicio para consultar nombre de productos
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/productos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarProductos (@HeaderParam("Authorization") String token) {
		return Response.status(Response.Status.OK).entity(inventarioService.darInfoProductos()).build();
	}

	/**
	 * Servicio para consultar informacion de un producto
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException si no existe el producto o no esta listado
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/producto/{idProducto}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarProducto (@HeaderParam("Authorization") String token, @PathParam("idProducto") Long idProducto) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(inventarioService.darProducto(idProducto)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST,e);
		}
	}

	/**
	 * Servicio que busca busca los producto a partir de un texto
	 * @param HeaderParam accesToken
	 * @param patronProducto texto al interior del nombre
	 * @return productos cuya nombre contenga el texto
	 * @throws WSException 
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/productos/darProductos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarProductoPorPatron (@HeaderParam("Authorization") String token, @QueryParam("productText") String textoProducto, @QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		if(textoProducto.equals("")) {
			throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.texto.producto", String.class), Response.Status.BAD_REQUEST);
		}
		if(limite==null) {
			throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
		}
		if(posInicial==null) {
			posInicial = 0;
		}
		return Response.status(Response.Status.OK).entity(inventarioService.darProductosPorPatron(textoProducto,posInicial,limite)).build();
	}

	/**
	 * Servicio para consultar nombre de productos
	 * @param HeaderParam accessToken
	 * @return nombres de prodcutos
	 */
	@PermitAll
	@GET
	@Path("/categorias")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarCategorias (@HeaderParam("Authorization") String token) {
		return Response.status(Response.Status.OK).entity(inventarioService.darInfoCategorias()).build();
	}

	/**
	 * Servicio para consultar los productos pertenecientes a una categoria
	 * @param HeaderParam accesToken
	 * @return productos
	 * @throws WSException si no existe la categoria
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/categoria/{idCategoria}/productos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarProductosCategoria (@HeaderParam("Authorization") String token, @PathParam("idCategoria") Long idCategoria,@QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		try {
			if(limite==null) {
				throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
			}
			if(posInicial==null) {
				posInicial = 0;
			}
			return Response.status(Response.Status.OK).entity(inventarioService.darProductoCategoria(idCategoria,posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST,e);
		}
	}

	/**
	 * Persiste la info de producto en la base de datos
	 * @param HeaderParam accessToken
	 * @return codigo respuesta 204 si todo salio bien
	 * @throws WSException si hay problemas creando producto
	 */
	@RolesAllowed({"PROVEEDOR","ADMIN","TESTER"})
	@POST
	@Path("/producto")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarProducto(@HeaderParam("Authorization") String token, AgregarProductoParams producto) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(inventarioService.crearProducto(producto,jwt.getSubject())).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(),Response.Status.BAD_REQUEST , e);
		}
	}
	
	/**
	 * Servicio para consultar las cotozaciones pertenecientes al proveedor
	 * @param HeaderParam accesToken
	 * @return productos
	 * @throws WSException si no se envia el limite de registros
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/productos/proveedor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response darProductosProveedor(@HeaderParam("Authorization") String token, EmailParam email,@QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		if(limite==null) {
			throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
		}
		if(posInicial==null) {
			posInicial = 0;
		}
		try {
			return Response.status(Response.Status.OK).entity(inventarioService.darProductosProveedor(email.getEmail(),posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST,e);
		}
	}

	/**
	 * Persiste la info de producto en la base de datos
	 * @param HeaderParam accessToken
	 * @return codigo respuesta 204 si todo salio bien
	 * @throws WSException si hay problemas creando producto
	 */
	@RolesAllowed({"SOLICITANTE","ADMIN","TESTER"})
	@POST
	@Path("/cotizacion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarCotizacion(@HeaderParam("Authorization") String token, AgregarCotizacionParams producto) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(inventarioService.crearCotizacion(producto,jwt.getSubject())).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(),Response.Status.BAD_REQUEST , e);
		}
	}

	/**
	 * Persiste la info de la categoria en la base de datos
	 * @param HeaderParam accessToken
	 * @return codigo respuesta 204 si todo salio bien
	 * @throws WSException si hay problemas creando producto
	 */
	@RolesAllowed({"GERENTE","ADMIN","TESTER"})
	@POST
	@Path("/categoria")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarCategoria(@HeaderParam("Authorization") String token, AgregarCategoriaParams categoria) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(inventarioService.agregarCategoria(categoria)).build();
		} catch (Exception e) {
			throw new WSException(e.getMessage(),Response.Status.BAD_REQUEST , e);
		}
	}

	/**
	 * Actualiza el valor del producto y la propiedad de listar
	 * @param HeaderParam accessToken
	 * @param PathParam id del producto a modificar
	 * @param PublicarProductoParams objeto con el valor del fee
	 * @return codigo respuesta 204 si todo salio bien
	 * @throws WSException si hay problemas creando producto
	 */
	@RolesAllowed({"GERENTE","ADMIN","TESTER"})
	@PUT
	@Path("/publicarProducto/{idProducto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response publicarProducto(@HeaderParam("Authorization") String token, @PathParam("idProducto") Long idProducto, PublicarProductoParams valorFee) throws WSException {
		try {
			inventarioService.publicarProducto(idProducto, valorFee);
		} catch (Exception e) {
			throw new WSException(e.getMessage(),Response.Status.BAD_REQUEST , e);
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	/**
	 * Servicio para consultar informacion de un producto
	 * @param HeaderParam accesToken
	 * @return nombres de prodcutos
	 * @throws WSException si no existe el producto o no esta listado
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/cotizacion/{idCotizacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarCotizacion (@HeaderParam("Authorization") String token, @PathParam("idCotizacion") Long idProducto) throws WSException {
		try {
			return Response.status(Response.Status.OK).entity(inventarioService.darCotizacion(idProducto)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST,e);
		}
	}
	
	/**
	 * Servicio para consultar las cotozaciones pertenecientes a una categoria
	 * @param HeaderParam accesToken
	 * @return productos
	 * @throws WSException si no existe la categoria
	 */
	@RolesAllowed({"GERENTE","SOLICITANTE","TESTER"})
	@GET
	@Path("/categoria/{idCategoria}/cotizaciones")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  consultarCotizacionesCategoria (@HeaderParam("Authorization") String token, @PathParam("idCategoria") Long idCategoria,@QueryParam("startIndex") Integer posInicial,@QueryParam("limit") Integer limite) throws WSException {
		try {
			if(limite==null) {
				throw new WSException(ConfigProvider.getConfig().getValue("mensaje.no.limite.filtro", String.class), Response.Status.BAD_REQUEST);
			}
			if(posInicial==null) {
				posInicial = 0;
			}
			return Response.status(Response.Status.OK).entity(inventarioService.darCorizacionesCategoria(idCategoria,posInicial,limite)).build();
		} catch (ServiceException e) {
			throw new WSException(e.getMessage(), Response.Status.BAD_REQUEST,e);
		}
	}
}
