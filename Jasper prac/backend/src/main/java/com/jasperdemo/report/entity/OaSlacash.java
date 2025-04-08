package com.jasperdemo.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oa_slacash")
public class OaSlacash {

    @Id
    @Column(name = "docnumber")
    private String docnumber;

    @Column(name = "company")
    private int company ;

    @Column(name = "curramt")
    private Double curramt;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "doc-ref")
    private String docRef;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer", insertable = false, updatable = false)
    private OaCustomer customer;  // This should match the field in OaCustomer

    @ManyToOne
    @JoinColumn(name = "batch", referencedColumnName = "batch", insertable = false, updatable = false)
    private OaBatch batch;

    @Column(name = "doc_date")
    private LocalDate docdate;

    // Getters and Setters
}
