package br.gov.mme.exceptions;

public class ArquivoDeTipoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "Arquivo de tipo Inválido!";
    
    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
