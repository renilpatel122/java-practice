package com.jasperdemo.report.service;

import com.jasperdemo.report.entity.CashAuditHeader;
import com.jasperdemo.report.repository.cashAuditBatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class cashAuditHeaderService {
    @Autowired
    private cashAuditBatchRepo cashAuditRepo ;

    public CashAuditHeader getAuditHeader(String batch){
        return cashAuditRepo.findByBatch(batch) ;
    }
}
