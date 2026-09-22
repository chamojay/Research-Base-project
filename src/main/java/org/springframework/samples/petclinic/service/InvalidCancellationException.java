package org.springframework.samples.petclinic.service;

/**
 * Exception thrown when a visit cancellation request violates business rules
 * (e.g., attempt to cancel an already-cancelled visit or providing a blank reason).
 */
public class InvalidCancellationException extends RuntimeException {

    public InvalidCancellationException(String message) {
        super(message);
    }
}
