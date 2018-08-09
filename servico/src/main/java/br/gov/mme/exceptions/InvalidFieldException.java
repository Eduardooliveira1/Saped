package br.gov.mme.exceptions;

public class InvalidFieldException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String message;

    protected InvalidFieldException(String entity, String field) {
        this.message = ExceptionMessages.CREATE_INVALID_FIELD.message(entity, field);
    }

    public InvalidFieldException() {
        this("", "");
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
