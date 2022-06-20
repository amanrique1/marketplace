package com.marketplace.exceptions;

import javax.ws.rs.core.Response;

public class WSException extends Exception {

	/**
	 * Mensaje de error
	 */
	private String mensaje;

	/**
	 * Codigo de respuesta del WS
	 */
	private Response.Status status;
	
	/**
	 * Constructor
	 * @param mensaje
	 * @param status codigo de respuesta
	 * @param throwable error que se genero
	 */
	public WSException(String mensaje, Response.Status status, Throwable throwable) {
		super(mensaje,throwable);
		this.mensaje = mensaje;
		this.status = status;
	}
	
	/**
	 * Constructor
	 * @param mensaje
	 * @param status codigo de respuesta
	 */
	public WSException(String message, Response.Status status) {
		super(message);
		this.mensaje = message;
		this.status = status;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return mensaje;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.mensaje = message;
	}

	/**
	 * @return the status
	 */
	public Response.Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Response.Status status) {
		this.status = status;
	}
}