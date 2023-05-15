package com.ecommerce.order.exception;

/**
 * @author Himanshu
 * The InvalidDetailsException will be used when an DTO contains invalid details.
 */
public class InvalidDetailsException extends Exception {

    /**
     * serial version id.
     */
    private static final long serialVersionUID = 7292546645948316284L;

    /**
     * @param message - The message to be send in the exception.
     */
    public InvalidDetailsException(final String message) {
        super(message);
    }
}
