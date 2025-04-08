package com.jasperdemo.report.service;

import com.jasperdemo.report.DTO.Parameter;
import com.jasperdemo.report.DTO.cashAuditDTO;
import com.jasperdemo.report.entity.*;
import com.jasperdemo.report.repository.cashAuditRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.jasperdemo.report.DTO.cashAuditHeaderDTO ;

import javax.management.Query;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class cashAuditService {
    @Autowired
    private cashAuditRepo cashRepo;

    @PersistenceContext
    private EntityManager entityManager;


    public List<cashAuditDTO> getCashAuditData(Map<String, String> params) {
        // Step 1: Fetch parameters based on reportId (this part is dynamic in your case)
        Parameter parameter1 = new Parameter(2, "batchId", "String", 1L);
        Parameter parameter2 = new Parameter(3, "company", "String", 1L);

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);

        // Step 2: Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<cashAuditDTO> criteriaQuery = criteriaBuilder.createQuery(cashAuditDTO.class);
        Root<OaSlacash> oaSlacashRoot = criteriaQuery.from(OaSlacash.class);

        // Join OaCustomer (because batch is not an entity relation)
        Join<OaSlacash, OaCustomer> customerJoin = oaSlacashRoot.join("customer", JoinType.INNER); // Ensure proper relation

        // Step 3: Prepare selection for cashAuditDTO
        criteriaQuery.select(criteriaBuilder.construct(
                cashAuditDTO.class,
                oaSlacashRoot.get("docnumber"),
                oaSlacashRoot.get("curramt"),
                oaSlacashRoot.get("discount"),
                oaSlacashRoot.get("docRef"),
                oaSlacashRoot.get("batch"),  // Keep batch as is
                customerJoin.get("customer"),
                customerJoin.get("name"),
                oaSlacashRoot.get("docdate"),
                criteriaBuilder.diff(oaSlacashRoot.get("curramt"), oaSlacashRoot.get("discount")),
                criteriaBuilder.sum(criteriaBuilder.diff(oaSlacashRoot.get("curramt"), oaSlacashRoot.get("discount"))),
                criteriaBuilder.sum(oaSlacashRoot.get("discount"))
        ));

        // Step 4: Create predicates for dynamic filtering
        List<Predicate> predicates = new ArrayList<>();

        parameters.forEach(param -> {
            String paramName = param.getName();
            String paramType = param.getType();

            if (params.containsKey(paramName)) {
                Object paramValue = params.get(paramName);
                Path<Object> path = getPathForParam(paramName, oaSlacashRoot, customerJoin);

                // Based on the type of the parameter, construct the correct Predicate
                switch (paramType) {
                    case "String":
                        predicates.add(criteriaBuilder.like(path.as(String.class), "%" + paramValue + "%"));
                        break;
                    case "Date":
                        LocalDate dateValue = LocalDate.parse((String) paramValue, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(path.as(LocalDate.class), dateValue));
                        break;
                    case "Integer":
                        predicates.add(criteriaBuilder.equal(path.as(Integer.class), paramValue));
                        break;
                }
            }
        });

        // Step 5: Apply predicates (WHERE conditions) to the query
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Step 6: Add grouping for non-aggregated fields to comply with SQL mode ONLY_FULL_GROUP_BY
        criteriaQuery.groupBy(
                oaSlacashRoot.get("docnumber"),
                oaSlacashRoot.get("curramt"),
                oaSlacashRoot.get("discount"),
                oaSlacashRoot.get("docRef"),
                oaSlacashRoot.get("batch"),  // Group by batch in the query
                customerJoin.get("customer"),
                customerJoin.get("name"),
                oaSlacashRoot.get("docdate")
        );

        // Step 7: Execute the query
        TypedQuery<cashAuditDTO> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Path<Object> getPathForParam(String paramName, Root<OaSlacash> oaSlacashRoot, Join<OaSlacash, OaCustomer> customerJoin) {
        switch (paramName) {
            case "batch":
                return oaSlacashRoot.get("batch"); // Direct access to the batch field in OaSlacash
            case "currency":
            case "docId":
                return customerJoin.get(paramName); // Fields from OaCustomer
            case "customer":
            case "name":
                return customerJoin.get(paramName); // Fields from OaCustomer
            case "docnumber":
            case "curramt":
            case "discount":
            case "docRef":
            case "docdate":
                return oaSlacashRoot.get(paramName); // Fields from OaSlacash
            default:
                throw new IllegalArgumentException("Unknown parameter: " + paramName);
        }
    }


    private List<Parameter> fetchParameters() {
        // Fetch the parameters dynamically, you can replace this with a DB call to fetch
        // parameters from your 'Parameter' table or configuration.
        // Example hardcoded list for testing:
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(2, "batchId", "String", 1L));
        parameters.add(new Parameter(3, "company", "String", 1L));
//        parameters.add(new Parameter(4, "docRef", "String", 1L));
//        parameters.add(new Parameter(5, "docdate", "Date", 1L));
        return parameters;
    }

    public cashAuditHeaderDTO convertEntityToDTO(CashAuditHeader entity) {
        cashAuditHeaderDTO dto = new cashAuditHeaderDTO();
      dto.setBatchId(entity.getBatch());
      dto.setYearNo(entity.getYearno());
      dto.setPeriod(entity.getPeriod());
      dto.setBatchDate(entity.getBatchDate());
      dto.setUserId(entity.getUuserid());
      dto.setBatchledger(entity.getLedger());
      dto.setBatchCurrency(entity.getCurrency());
        return dto;
    }
    public String exportReport(List<cashAuditDTO> reports , CashAuditHeader headers) throws FileNotFoundException, JRException {


        File file = ResourceUtils.getFile("classpath:cashAudit1.jrxml");


        try {
            JRBeanCollectionDataSource reportsDataSource = new JRBeanCollectionDataSource(reports);
//            JRBeanCollectionDataSource headersDataSource = new JRBeanCollectionDataSource(headerDto);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DTO1DataSource", reportsDataSource);
            parameters.put("DTO2DataSource", headers);


            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters , reportsDataSource);


            HtmlExporter exporter = new HtmlExporter();


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
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
            System.out.println("Error ====================" + e.getMessage());
            throw new JRException("Error generating HTML report", e);
        }

    }


}
