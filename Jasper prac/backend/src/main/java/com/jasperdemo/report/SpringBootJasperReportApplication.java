package com.jasperdemo.report;

import com.jasperdemo.report.DTO.cashAuditDTO;
import com.jasperdemo.report.entity.CashAuditHeader;
import com.jasperdemo.report.entity.PaymentProposalReport;
import com.jasperdemo.report.repository.PaymentProposalRepository;
import com.jasperdemo.report.service.ReportService;
import com.jasperdemo.report.service.cashAuditHeaderService;
import com.jasperdemo.report.service.cashAuditService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@RestController
public class SpringBootJasperReportApplication {


    @Autowired
    private PaymentProposalRepository repository;
    @Autowired
    private ReportService service;

    @Autowired
    private cashAuditService cashAuditService ;


    @Autowired
    private cashAuditHeaderService cashService ;

    @GetMapping("/{batchid}")
    public CashAuditHeader getHeader(@PathVariable String batchid){
        return cashService.getAuditHeader(batchid) ;

    }
    @PostMapping("/fetch-data")
    public String getbatchDetails(@RequestParam Map<String, String> params) throws JRException, FileNotFoundException {
        List<cashAuditDTO> cashAuditDetails = cashAuditService.getCashAuditData(params) ;
       CashAuditHeader headers = cashService.getAuditHeader(params.get("batchId").toString()) ;
        return cashAuditService.exportReport(cashAuditDetails , headers) ;
    }
    @GetMapping("/ok")
    public String healthCheck(){
        return "Everything good" ;
    }

    @GetMapping("/getEmployees")
    public List<PaymentProposalReport> getRecords() {
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }

    @GetMapping("/report/{startDate}/{endDate}")
    public String report(@PathVariable String startDate , @PathVariable String endDate) throws  FileNotFoundException , JRException
    {
        return service.exportReport(startDate , endDate) ;
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJasperReportApplication.class, args);
    }

}
