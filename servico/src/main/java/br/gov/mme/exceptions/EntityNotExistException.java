package br.gov.mme.exceptions;

public class EntityNotExistException extends Exception{

    private static final String MENSSAGE = "A entidade com o id buscado não existe!";

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage(){
        return MENSSAGE;
    }

}
