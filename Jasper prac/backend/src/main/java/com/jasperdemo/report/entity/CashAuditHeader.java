package com.jasperdemo.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "oa_batch")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CashAuditHeader {
    @Id
    @Column(name = "batch")
    private String batch;
    @Column(name = "ledger")
    private String ledger;
    @Column(name = "doc-id")
    private String docid  ;
    @Column(name = "uuserid")
    private String uuserid  ;
    @Column(name = "enttot")
    private Double enttot  ;
    @Column(name = "yearno")
    private Double yearno ;
    @Column(name = "period")
    private Double period ;
    @Column(name = "currency")
    private String currency ;

    @Column(name = "batchDate")
    private String batchDate ;
}
