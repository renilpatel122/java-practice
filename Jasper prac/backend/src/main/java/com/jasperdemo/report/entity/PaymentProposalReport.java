package com.jasperdemo.report.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentProposalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String batch;
    private String docId;
    private String currency;
    private String description;
    private String userLed;
    private LocalDate payDate;
    private String period;
    private Double total;
    private String supplierName;
    private String payeeName;
    private String addrCode;
    private Double amount;
    private Double discount;
    private Double outstanding;
    private Double accumTotal;

    @Override
    public String toString() {
        return "PaymentProposalReport{" +
                "id=" + id +
                ", batch='" + batch + '\'' +
                ", docId='" + docId + '\'' +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", userLed='" + userLed + '\'' +
                ", payDate=" + payDate +
                ", period='" + period + '\'' +
                ", total=" + total +
                ", supplierName='" + supplierName + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", addrCode='" + addrCode + '\'' +
                ", amount=" + amount +
                ", discount=" + discount +
                ", outstanding=" + outstanding +
                ", accumTotal=" + accumTotal +
                '}';
    }

}
