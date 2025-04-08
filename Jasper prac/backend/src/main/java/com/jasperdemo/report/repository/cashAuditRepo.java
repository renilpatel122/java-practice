package com.jasperdemo.report.repository;

import com.jasperdemo.report.DTO.cashAuditDTO;
import com.jasperdemo.report.entity.OaSlacash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface cashAuditRepo extends JpaRepository<OaSlacash , String > {
    @Query("SELECT new com.jasperdemo.report.DTO.cashAuditDTO(sc.docnumber, sc.curramt, sc.discount, sc.docRef, b.currency, " +
            "b.batch, b.docId, c.customer, c.name, sc.docdate, (sc.curramt - sc.discount), " +
            "SUM(sc.curramt - sc.discount) OVER () AS totalAmount, SUM(sc.discount) OVER () AS totalDiscount) " +
            "FROM OaSlacash sc " +
            "JOIN OaBatch b ON b.batch = sc.batch " +
            "JOIN OaCustomer c ON sc.customer = c.customer " +
            "WHERE b.batch = :batchId AND sc.company = :companyId" +
            " AND sc.docdate BETWEEN :startDate AND :endDate"
    )
    List<cashAuditDTO> findByBatchId(@Param("batchId") String batchId , @Param("companyId") int companyId, @Param("startDate") LocalDate startDate ,@Param("endDate") LocalDate endDate   );
}
