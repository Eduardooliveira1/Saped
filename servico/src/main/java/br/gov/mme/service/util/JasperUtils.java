package br.gov.mme.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import br.gov.mme.enumeration.ReportType;
import br.gov.mme.exceptions.ArquivoDeTipoInvalidoException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;


public final class JasperUtils {

    private JasperUtils() {
    }

    public static void getReportData(String reportJxrmlPath, ReportType reportType, List<?> voList,
            HttpServletResponse response, Logger log)
            throws ArquivoDeTipoInvalidoException, RelatorioException, LeituraBufferException {
        try {
            JasperPrint jasperPrint = compileReport(reportJxrmlPath, voList);
            ServletOutputStream exportData = response.getOutputStream();
            exportReport(jasperPrint, exportData, reportType);
        } catch (JRException e) {
            log.error(e.getMessage(), e);
            throw new RelatorioException(e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new LeituraBufferException(e);
        }

    }

    private static JasperPrint compileReport(String reportJxrmlPath, List<?> voList) throws JRException {
        InputStream reportJxrml = JasperUtils.class.getResourceAsStream(reportJxrmlPath);
        JasperDesign jrxmlSource = JRXmlLoader.load(reportJxrml);
        JasperReport report = JasperCompileManager.compileReport(jrxmlSource);
        return JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(voList));
    }

    private static JRXlsExporter getXlsExporter(JasperPrint jasperPrint, OutputStream exportData) {
        JRXlsExporter xlsExporter = new JRXlsExporter();

        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportData));

        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();

        xlsExporter.setConfiguration(xlsReportConfiguration);

        return xlsExporter;
    }

    private static void exportReport(JasperPrint jasperPrint, OutputStream exportData, ReportType reportType)
            throws JRException, ArquivoDeTipoInvalidoException {
        switch (reportType) {
        case XLS:
            getXlsExporter(jasperPrint, exportData).exportReport();
            break;
        default:
            throw new ArquivoDeTipoInvalidoException();
        }
    }

}
