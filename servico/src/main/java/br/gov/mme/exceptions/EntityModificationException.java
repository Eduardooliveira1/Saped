package br.gov.mme.exceptions;

public class EntityModificationException extends Exception {

    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "Erro ao alterar dados. Tabela: %s .";

    private final String tabela;

    public EntityModificationException(String tabela) {
        super();
        this.tabela = tabela;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, tabela);
    }

}
