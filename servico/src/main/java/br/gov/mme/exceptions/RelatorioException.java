package br.gov.mme.exceptions;

import net.sf.jasperreports.engine.JRException;

public class RelatorioException extends JRException {

    private static final long serialVersionUID = 1L;

    public static final String EXCPT_MESSAGE = "Ocorreu um erro ao exportar os dados.";

    public RelatorioException(JRException e)
    {
        super(EXCPT_MESSAGE, e);
    }

}
