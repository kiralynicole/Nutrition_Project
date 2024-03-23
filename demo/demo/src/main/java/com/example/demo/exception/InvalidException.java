package com.example.demo.exception;

/**
 * The {@code InvalidException} class represents a custom exception that signals an invalid operation
 * or value within the application's domain. It extends the standard {@link Exception} class.
 * <p>
 * This exception should be thrown when an operation cannot be completed due to invalid input
 * or an inappropriate state of an object. For example, it could be used to indicate invalid
 * parameters passed to a method, or when an operation's preconditions are not met.
 * </p>
 */
public class InvalidException extends Exception {
    /**
     * Constructs a new {@code InvalidException} with the specified detail message.
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidException(String message){
        super(message);
    }
}
