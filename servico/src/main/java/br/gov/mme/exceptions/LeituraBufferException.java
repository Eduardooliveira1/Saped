package br.gov.mme.exceptions;

import java.io.IOException;

public class LeituraBufferException extends IOException {

    private static final long serialVersionUID = 1L;
    
    public static final String EXCPT_MESSAGE = "Ocorreu um erro ao acessar o arquivo.";

    public LeituraBufferException(IOException e) {
        super(EXCPT_MESSAGE, e);
    }

}
