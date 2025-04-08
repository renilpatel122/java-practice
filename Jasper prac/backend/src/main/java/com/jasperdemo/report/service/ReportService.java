package com.jasperdemo.report.service;

import com.jasperdemo.report.entity.Employee;
import com.jasperdemo.report.entity.PaymentProposalReport;
import com.jasperdemo.report.repository.EmployeeRepository;
import com.jasperdemo.report.repository.PaymentProposalRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository repository;


//    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "C:\\Users\\Public\\Report";
//
//        List<Employee> employees = repository.findAll();
//        File file = ResourceUtils.getFile("classpath:employees.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Bonhams");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
//        }
//        if (reportFormat.equalsIgnoreCase("csv")) {
//            Exporter exporter = new JRCsvExporter();
//            ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
//            SimpleWriterExporterOutput exporterOutput = new SimpleWriterExporterOutput(path + "\\employees.csv");
//
//            exporter.setExporterInput(exporterInput);
//            exporter.setExporterOutput(exporterOutput);
//
//            // Set CSV-specific configurations
//            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
//            exporter.setConfiguration(configuration);
//
//            exporter.exportReport();
//        }
//
//        return "report generated in path : " + path;
//    }


    @Autowired
    private PaymentProposalRepository paymentProposalReportRepository;

    public String exportReport(String reportFormat ) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Public\\Report";

        List<PaymentProposalReport> reports = paymentProposalReportRepository.findAll();
        File file = ResourceUtils.getFile("classpath:payment_proposal_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Bonhams");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\payment_proposal_report.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\payment_proposal_report.pdf");
        }
        if (reportFormat.equalsIgnoreCase("csv")) {
            Exporter exporter = new JRCsvExporter();
            ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            SimpleWriterExporterOutput exporterOutput = new SimpleWriterExporterOutput(path + "\\payment_proposal_report.csv");

            exporter.setExporterInput(exporterInput);
            exporter.setExporterOutput(exporterOutput);

            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            exporter.setConfiguration(configuration);

            exporter.exportReport();
        }

        return "Report generated in path : " + path;
    }

    public String exportReport(String startDate , String endDate) throws FileNotFoundException, JRException {

        LocalDate stDate = LocalDate.parse(startDate) ;
        LocalDate enDate = LocalDate.parse(endDate) ;

        List<PaymentProposalReport> reports = paymentProposalReportRepository.findAll();

        List<PaymentProposalReport> filteredReports = reports.stream()
                .filter(report -> {
                    LocalDate payDate = report.getPayDate() ;
                    return (payDate != null && !payDate.isBefore(stDate) && !payDate.isAfter(enDate));
                })
                .collect(Collectors.toList());

        File file = ResourceUtils.getFile("classpath:payment_proposal_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(filteredReports);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Bonhams");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        HtmlExporter exporter = new HtmlExporter() ;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));

            // Optional: Configure HTML report (e.g., set embedded resources)
            SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
            configuration.setRemoveEmptySpaceBetweenRows(true);
            exporter.setConfiguration(configuration);

            // Export the report
            exporter.exportReport();
        String output = outputStream.toString("UTF-8");
            // Convert the output stream to a string and return it
            return outputStream.toString("UTF-8");
        } catch (Exception e) {
            throw new JRException("Error generating HTML report", e);
        }

    }



}
