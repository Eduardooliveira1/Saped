package br.gov.mme.exceptions;

public class CNPJInvalidoException extends Exception{
    
    private static final long serialVersionUID = 1L;

    public static final String MESSAGE = "Erro ao adicionar Pessoa Jurídica!\n"
            + "Descrição: CNPJ informado é inválido!";
    
    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
