package com.marketplace.api;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;

@Singleton
public class ApiResource {

	private final AuthenticationResource authenticationResource;
	private final FacturacionResource facturacionResource;
	private final InventarioResource inventarioResource;
	private final PQRSResource pqrsResource;
	private final ProveedorResource proveedorResource;

	@Inject
	public ApiResource(
			AuthenticationResource authenticationResource,FacturacionResource facturacionResource,
			InventarioResource inventarioResource,PQRSResource pqrsResource,ProveedorResource proveedorResource) {
		this.authenticationResource = authenticationResource;
		this.facturacionResource = facturacionResource;
		this.inventarioResource = inventarioResource;
		this.pqrsResource = pqrsResource;
		this.proveedorResource = proveedorResource;
	}

	/**
	 * @return the authenticationResource
	 */
	@Path("/auth")
	public AuthenticationResource getAuthenticationResource() {
		return authenticationResource;
	}

	/**
	 * @return the facturacionResource
	 */
	@Path("/facturacion")
	public FacturacionResource getFacturacionResource() {
		return facturacionResource;
	}

	/**
	 * @return the inventarioResource
	 */
	@Path("/inventario")
	public InventarioResource getInventarioResource() {
		return inventarioResource;
	}

	/**
	 * @return the pqrsResource
	 */
	@Path("/pqrs")
	public PQRSResource getPqrsResource() {
		return pqrsResource;
	}

	/**
	 * @return the proveedorResource
	 */
	@Path("/proveedor")
	public ProveedorResource getProveedorResource() {
		return proveedorResource;
	}

}