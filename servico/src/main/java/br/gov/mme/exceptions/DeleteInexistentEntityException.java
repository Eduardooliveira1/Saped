package br.gov.mme.exceptions;

public class DeleteInexistentEntityException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String message;
    
    protected DeleteInexistentEntityException(String entity) {
        this.message = ExceptionMessages.DELETE_INEXISTENT_ID.message(entity);
    }
    
    public DeleteInexistentEntityException() {
        this("");
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
