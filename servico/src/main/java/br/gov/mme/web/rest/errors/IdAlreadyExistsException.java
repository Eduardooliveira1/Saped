package br.gov.mme.web.rest.errors;

public class IdAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    private final BadRequestAlertException exception;

    public IdAlreadyExistsException(String idExistsCustomMenssage, String entityName) {
        this.exception = new BadRequestAlertException(ErrorConstants.CONSTRAINT_VIOLATION_TYPE,
                idExistsCustomMenssage, entityName, ErrorKeys.ID_EXISTS.error());
    }

    public BadRequestAlertException getException() {
        return this.exception;
    }

    @Override
    public String getMessage() {
        return exception.getDefaultMessage();
    }

}
