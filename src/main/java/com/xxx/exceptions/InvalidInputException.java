package com.xxx.exceptions;

/**
 * This exception is thrown in case of invalid input passed in the request.
 * 
 * @author Shekhar Suman
 * @version 1.0
 * @since 2017-02-03
 */
public class InvalidInputException extends Exception {

	private static final long serialVersionUID = -3388691834518234285L;

	/**
	 * Default constructor
	 */
	public InvalidInputException() {
		super();
	}

	/**
	 * Constructor with the exception message
	 * 
	 * @param msg
	 *            exception message
	 */
	public InvalidInputException(final String msg) {
		super(msg);
	}
}
