package org.qcri.micromappers.nlp.exception;

/**
 * An Micromappers-specific exception indicating that a required property has not been set.
 *
 * @author Kushal
 *
 */
public class PropertyNotSetException extends MicromappersException {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -4465084668381150539L;

	/**
	 * Creates an exception.
	 */
	public PropertyNotSetException() {
		super();
	}

    /**
     * Creates an exception with a given message.
     * 
     * @param message the message to include in the exception
     */
	public PropertyNotSetException(String message) {
		super(message);
	}

    /**
     * Creates an exception with a given cause
     * 
     * @param cause the cause
     */
	public PropertyNotSetException(Throwable cause) {
		super(cause);
	}

    /**
     * Creates an exception with a given message and cause
     * 
     * @param message the message
     * @param cause the cause
     */
	public PropertyNotSetException(String message, Throwable cause) {
		super(message, cause);
	}
}
