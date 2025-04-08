package com.jasperdemo.report.repository;

import com.jasperdemo.report.entity.CashAuditHeader;

import org.springframework.data.jpa.repository.JpaRepository;



public interface cashAuditBatchRepo extends JpaRepository<CashAuditHeader, String > {

    CashAuditHeader findByBatch(String batchId ) ;



}
