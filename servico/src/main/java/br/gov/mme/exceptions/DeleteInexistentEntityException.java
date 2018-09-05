package br.gov.mme.exceptions;

public class DeleteInexistentEntityException extends EntityModificationException {

    private static final long serialVersionUID = 1L;

    public static final String MESSAGE = "Descrição: Não foi possível excluir: Id buscado "
            + "não está nos registros.";

    public DeleteInexistentEntityException(String tabela) {
        super(tabela);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + MESSAGE;
    }

}
