package com.ecommerce.order.exception;

public class RecordNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3803193588532019121L;

	/**
     * @param message - The message to be send in the exception.
     */
    public RecordNotFoundException(final String message) {
        super(message);
    }

}
