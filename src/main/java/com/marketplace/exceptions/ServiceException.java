package com.marketplace.exceptions;

import javax.ws.rs.core.Response;

public class ServiceException extends Exception {

	/**
	 * Message of the exception
	 */
	private String message;

	/**
	 * status for the exception
	 */
	private Response.Status status;

	/**
	 * @param message message for the exception
	 * @param cause cause of exception
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message message for the exception
	 */
	public ServiceException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Constructor for the class
	 * @param message message for the exception
	 * @param status status for the exception
	 */
	public ServiceException(String message, Response.Status status) {
		super();
		this.message = message;
		this.status = status;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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