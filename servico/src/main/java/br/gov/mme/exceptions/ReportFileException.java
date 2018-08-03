package br.gov.mme.exceptions;

import java.io.FileNotFoundException;

public class ReportFileException extends FileNotFoundException {

    private static final long serialVersionUID = 1L;

    public static final String EXCPT_MESSAGE = "Ocorreu um erro ao salvar o arquivo exportado.";

    public ReportFileException(FileNotFoundException e) {
        super(EXCPT_MESSAGE);
        super.setStackTrace(e.getStackTrace());
    }

}

