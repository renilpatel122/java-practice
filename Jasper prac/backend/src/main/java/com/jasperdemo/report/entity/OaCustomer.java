package com.jasperdemo.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "oa_customer")
public class OaCustomer {

    @Id
    @Column(name = "customer")
    private String customer;

    @Column(name = "name")
    private String name;

    // Getters and Setters
}
