package br.gov.mme.service.util;

import java.io.File;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class JasperUtils {

    public File getFile(String relPath) throws JRException {
        JasperPrint jasperPrint = this.compileReport(relPath);

        File exportReportFile = new File("PagamentoReport.xls");

        JRXlsExporter xlsExporter = this.getExporter(jasperPrint, exportReportFile);
        xlsExporter.exportReport();

        return exportReportFile;
    }
    
    private JasperPrint compileReport(String relPath) throws JRException {
        JasperDesign jrxmlSource = JRXmlLoader.load(relPath);
        JasperReport report = JasperCompileManager.compileReport(jrxmlSource);
        return JasperFillManager.fillReport(report, null);
    }

    private JRXlsExporter getExporter(JasperPrint jasperPrint, File exportReportFile) {
        JRXlsExporter xlsExporter = new JRXlsExporter();

        xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));

        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();

        xlsReportConfiguration.setOnePagePerSheet(false);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsReportConfiguration.setDetectCellType(false);
        xlsReportConfiguration.setWhitePageBackground(false);
        xlsExporter.setConfiguration(xlsReportConfiguration);

        return xlsExporter;
    }
}
