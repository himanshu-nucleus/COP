package com.ecommerce.payment.exception;

public class RecordNotFoundException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8241835696610117843L;

	/**
     * @param message - The message to be send in the exception.
     */
    public RecordNotFoundException(final String message) {
        super(message);
    }

}
