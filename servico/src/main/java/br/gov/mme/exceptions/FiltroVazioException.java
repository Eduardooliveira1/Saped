package br.gov.mme.exceptions;

public class FiltroVazioException extends Exception {

    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "Filtro passado está vazio!";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
