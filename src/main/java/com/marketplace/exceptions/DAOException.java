package com.marketplace.exceptions;

public class DAOException extends Exception {

	/**
	 * Constructor
	 * @param message message for the exception
	 * @param cause cause of exception
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message message for the exception
	 */
	public DAOException(String message) {
		super(message);
	}
}