package br.gov.mme.exceptions;

public class EncapsuladaException extends Exception {

    private static final long serialVersionUID = 1L;

    private Exception exception;

    public EncapsuladaException(Exception exception){
        super(exception);
        this.exception = exception;
    }

    @Override
    public  String getMessage(){
        return exception.getMessage();
    }

}
