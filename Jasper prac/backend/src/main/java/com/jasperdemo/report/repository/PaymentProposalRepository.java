package com.jasperdemo.report.repository;

import com.jasperdemo.report.entity.PaymentProposalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentProposalRepository extends JpaRepository<PaymentProposalReport,Long> {
}
