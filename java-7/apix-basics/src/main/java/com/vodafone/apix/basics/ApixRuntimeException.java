/**
 * 
 */
package com.vodafone.apix.basics;

/**
 * @author Rainer.Bieniek@vodafone.com
 *
 */
public class ApixRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4605123232422163337L;

	/**
	 * 
	 */
	public ApixRuntimeException() {
	}

	/**
	 * @param message
	 */
	public ApixRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ApixRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApixRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
