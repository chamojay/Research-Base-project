package org.springframework.samples.petclinic.service;

/**
 * Exception thrown when attempting to cancel a visit that is already cancelled.
 */
public class VisitAlreadyCancelledException extends RuntimeException {

    public VisitAlreadyCancelledException(String message) {
        super(message);
    }
}
