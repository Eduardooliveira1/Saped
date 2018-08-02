package br.gov.mme.exceptions;

import java.io.FileNotFoundException;

public class FileException extends FileNotFoundException {

    private static final long serialVersionUID = 1L;

    public static final String EXCPT_MESSAGE = "Ocorreu um erro ao salvar o arquivo exportado.";

    public FileException() {
        super(EXCPT_MESSAGE);
    }

}

