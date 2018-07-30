package br.gov.mme.utils;

public class GenericException extends Exception {

    private static final long serialVersionUID = 1L;

    private final Exception exception;

    public GenericException(Exception exception) {
        super(exception.getClass().getName() + ": " + exception.getMessage());
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

}
