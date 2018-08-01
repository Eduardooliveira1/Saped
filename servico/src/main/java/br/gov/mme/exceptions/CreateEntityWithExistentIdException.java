package br.gov.mme.exceptions;

public class CreateEntityWithExistentIdException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private final String message;
    
    protected CreateEntityWithExistentIdException(String entity) {
        this.message = ExceptionMessages.CREATE_EXISTENT_ID.message(entity);
    }
    
    public CreateEntityWithExistentIdException() {
        this("");
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
