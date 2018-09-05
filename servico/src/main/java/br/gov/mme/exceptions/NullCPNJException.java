package br.gov.mme.exceptions;

public class NullCPNJException extends Exception {

    private static final long serialVersionUID = 1L;

    static final String MESSAGE = "CNPJ não encontrado na base de dados.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
