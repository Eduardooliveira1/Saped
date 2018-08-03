package br.gov.mme.exceptions;

public class CheckedInvalidArgumentException extends Exception {

    private static final long serialVersionUID = 1L;

    public CheckedInvalidArgumentException(String message) {
        super(message);
    }
}
