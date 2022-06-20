package com.marketplace.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LoggeableException {

	/**
	 * Main message to be logged. This messsage will
	 * always exists if some exception is thrown. This exception 
	 * may be of any type.
	 */
	private String message;

	/**
	 * This message is optional and will only be used
	 * when a not-controlled exception occurs. Here will
	 * be stored all its stack trace.
	 */
	private String cause;

	/**
	 * Empty constructor
	 */
	public LoggeableException() {
		super();
	}
	
	/**
	 * Constructor using object field
	 * @param objectToLog object to be assigned
	 */
	public LoggeableException(String message) {
		super();
		this.message = message;
	}
	
	/**
	 * @return the main exception message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the main exception message
	 * @param message the message to be assigned
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the optional cause of the not-controlled exception
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * Sets the optional cause of the not-controlled exception
	 * @param cause the optional cause of the not-controlled exception
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
}