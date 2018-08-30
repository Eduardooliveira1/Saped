package br.gov.mme.exceptions;

public enum ExceptionMessages {
    CREATE_EXISTENT_ID("Não foi possível criar a entidade (%s): Id já existe."), DELETE_INEXISTENT_ID(
            "Não foi possível excluir a entidade (%s): Id não existe."), CREATE_INVALID_FIELD(
                    "Não foi possível criar a entidade (%s): O campo (%s) já existe.");
    
    private final String message;
    
    ExceptionMessages(String message){
        this.message = message;
    }
    
    public String message(String entity) {
        return String.format(this.message, entity);
    }

    public String message(String entity, String field) {
        return String.format(this.message, entity, field);
    }
    
}
