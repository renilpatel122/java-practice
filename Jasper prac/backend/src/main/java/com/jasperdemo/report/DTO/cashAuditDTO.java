package com.jasperdemo.report.DTO;

import java.time.LocalDate;

public class cashAuditDTO {
    private String docnumber;
    private String customer;
    private String name;
    private String docRef;
    private String batch;
    private String docId;
    private String currency ;
    private Double curramt;
    private Double discount;
    private LocalDate docdate;

    private Double baseTotal ;

    private Double totalAmount ;
    private Double totalDiscount   ;


    public cashAuditDTO(String docnumber, Double curramt,  Double discount, String docRef, String currency , String batch, String docId, String customer, String name, LocalDate docdate , Double baseTotal , Double totalAmount , Double totalDiscount) {
        this.docnumber = docnumber;
        this.customer = customer;
        this.name = name;
        this.docRef = docRef;
        this.currency = currency ;
        this.batch = batch;
        this.docId = docId;
        this.curramt = curramt;
        this.discount = discount;
        this.docdate = docdate;
        this.baseTotal = baseTotal ;
        this.totalAmount = totalAmount ;
        this.totalDiscount = totalDiscount ;

    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Double getBaseTotal() {
        return baseTotal;
    }

    public void setBaseTotal(Double baseTotal) {
        this.baseTotal = baseTotal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public void setDocnumber(String docnumber) {
        this.docnumber = docnumber;
    }

    public Double getCurramt() {
        return curramt;
    }

    public void setCurramt(Double curramt) {
        this.curramt = curramt;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDocRef() {
        return docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDocdate() {
        return docdate;
    }

    public void setDocdate(LocalDate docdate) {
        this.docdate = docdate;
    }

    @Override
    public String toString() {
        return "cashAuditDTO{" +
                "docnumber='" + docnumber + '\'' +
                ", customer='" + customer + '\'' +
                ", name='" + name + '\'' +
                ", docRef='" + docRef + '\'' +
                ", batch='" + batch + '\'' +
                ", docId='" + docId + '\'' +
                ", currency='" + currency + '\'' +
                ", curramt=" + curramt +
                ", discount=" + discount +
                ", docdate='" + docdate + '\'' +
                ", baseTotal=" + baseTotal +
                '}';
    }
}
