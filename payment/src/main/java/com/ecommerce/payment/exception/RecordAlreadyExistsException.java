package com.ecommerce.payment.exception;

public class RecordAlreadyExistsException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2834022837509599717L;

	/**
     * @param message - The message to be send in the exception.
     */
    public RecordAlreadyExistsException(final String message) {
        super(message);
    }

}
