package br.gov.mme.service.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;

import br.gov.mme.enumeration.ReportType;
import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.web.rest.util.FilesUtil;
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


public class JasperUtils {

    public ByteArrayOutputStream getReportData(String relPath, ReportType reportType, List<?> voList)
            throws JRException, CheckedInvalidArgumentException, IOException {

        JasperPrint jasperPrint = this.compileReport(relPath, voList);

        File exportReportFile = new File((new StringBuilder("report").append(reportType)).toString());
        this.exportReport(jasperPrint, exportReportFile, reportType);

        return FilesUtil.fileToOutputStream(exportReportFile);
    }

    private JasperPrint compileReport(String relPath, List<?> voList) throws JRException {
        JasperDesign jrxmlSource = JRXmlLoader.load(relPath);
        JasperReport report = JasperCompileManager.compileReport(jrxmlSource);
        return JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(voList));
    }

    private JRXlsExporter getExporter(JasperPrint jasperPrint, File exportReportFile) {
        JRXlsExporter xlsExporter = new JRXlsExporter();

        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));

        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();

        xlsExporter.setConfiguration(xlsReportConfiguration);

        return xlsExporter;
    }

    private void exportReport(JasperPrint jasperPrint, File exportReportFile, ReportType reportType)
            throws JRException, CheckedInvalidArgumentException {
        switch (reportType) {
        case XLS:
            this.getExporter(jasperPrint, exportReportFile).exportReport();
        default:
            throw new CheckedInvalidArgumentException("Arquivo de tipo Inválido!");
        }
    }

}
