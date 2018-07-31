package br.gov.mme.web.rest.errors;

public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private final BadRequestAlertException exception;

    public EntityNotFoundException(String entityNotFoundCustomMenssage, String entityName) {
        this.exception = new BadRequestAlertException(ErrorConstants.ENTITY_NOT_FOUND_TYPE,
                entityNotFoundCustomMenssage, entityName, ErrorKeys.ID_INEXISTENT.error());
    }

    public BadRequestAlertException getException() {
        return this.exception;
    }

    @Override
    public String getMessage() {
        return exception.getDefaultMessage();
    }

}
