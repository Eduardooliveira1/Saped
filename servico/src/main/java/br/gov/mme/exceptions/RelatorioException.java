package br.gov.mme.web.rest.errors;

import net.sf.jasperreports.engine.JRException;

public class RelatorioException extends JRException {

    private static final long serialVersionUID = 1L;

    public static final String EXCPT_MESSAGE = "Ocorreu um erro ao exportar os dados.";
    
    public RelatorioException()
    {
        super(EXCPT_MESSAGE);
    }

}
