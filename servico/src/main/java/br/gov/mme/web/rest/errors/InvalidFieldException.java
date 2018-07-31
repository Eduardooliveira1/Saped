package br.gov.mme.web.rest.errors;

public class InvalidFieldException extends Exception {

    private static final long serialVersionUID = 1L;

    private final BadRequestAlertException exception;

    public InvalidFieldException(String entityName, ErrorKeys key) {
        this("Um novo registro não pode ter um ID", entityName, key);
    }

    public InvalidFieldException(String invalidFieldCustomMenssage, String entityName, ErrorKeys key) {
        this.exception = new BadRequestAlertException(ErrorConstants.ENTITY_NOT_FOUND_TYPE, 
                invalidFieldCustomMenssage, entityName, key.error());
    }

    public BadRequestAlertException getException() {
        return this.exception;
    }

    @Override
    public String getMessage() {
        return exception.getDefaultMessage();
    }

}
