package com.jasperdemo.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "oa_batch")
public class OaBatch {

    @Id
    @Column(name = "batch")
    private String batch;

    @Column(name = "doc-id")
    private String docId;

    @Column(name = "currency")
    private String currency ;

}
