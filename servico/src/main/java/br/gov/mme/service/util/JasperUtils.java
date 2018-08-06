package br.gov.mme.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;

import br.gov.mme.enumeration.ReportType;
import br.gov.mme.exceptions.CheckedInvalidArgumentException;
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

    public static ByteArrayOutputStream getReportData(InputStream reportJxrml, ReportType reportType, List<?> voList)
            throws JRException, CheckedInvalidArgumentException, IOException {

        JasperPrint jasperPrint = compileReport(reportJxrml, voList);

        ByteArrayOutputStream exportData = new ByteArrayOutputStream();
        exportReport(jasperPrint, exportData, reportType);

        return exportData;
    }

    private static JasperPrint compileReport(InputStream reportJxrml, List<?> voList) throws JRException {
        JasperDesign jrxmlSource = JRXmlLoader.load(reportJxrml);
        JasperReport report = JasperCompileManager.compileReport(jrxmlSource);
        return JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(voList));
    }

    private static JRXlsExporter getExporter(JasperPrint jasperPrint, OutputStream exportData) {
        JRXlsExporter xlsExporter = new JRXlsExporter();

        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportData));

        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();

        xlsExporter.setConfiguration(xlsReportConfiguration);

        return xlsExporter;
    }

    private static void exportReport(JasperPrint jasperPrint, OutputStream exportData, ReportType reportType)
            throws JRException, CheckedInvalidArgumentException {
        switch (reportType) {
        case XLS:
            getExporter(jasperPrint, exportData).exportReport();
            break;
        default:
            throw new CheckedInvalidArgumentException("Arquivo de tipo Inválido!");
        }
    }

}
