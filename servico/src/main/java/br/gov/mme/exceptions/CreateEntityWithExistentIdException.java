package br.gov.mme.exceptions;

public class CreateEntityWithExistentIdException extends EntityModificationException {
    
    private static final long serialVersionUID = 1L;
    
    public static final String MESSAGE = "Descricao: Não foi possível inserir: Id já existe.";
    
    public CreateEntityWithExistentIdException(String tabela) {
        super(tabela);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + MESSAGE;
    }

}
