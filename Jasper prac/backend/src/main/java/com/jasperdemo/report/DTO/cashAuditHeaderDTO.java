package com.jasperdemo.report.DTO;

public class cashAuditHeaderDTO {

    private String batchId ;
    private Double  yearNo ;
    private Double period ;
    private String batchDate ;
    private String userId ;
    private String batchledger ;

    private String batchCurrency ;



    public cashAuditHeaderDTO(){

    }
    public cashAuditHeaderDTO(String batchId, Double yearNo, Double period, String batchDate, String userId, String batchledger, String batchCurrency) {
        this.batchId = batchId;
        this.yearNo = yearNo;
        this.period = period;
        this.batchDate = batchDate;
        this.userId = userId;
        this.batchledger = batchledger;
        this.batchCurrency = batchCurrency ;
    }

    @Override
    public String toString() {
        return "cashAuditHeaderDTO{" +
                "batch='" + batchId + '\'' +
                ", yearNo=" + yearNo +
                ", period=" + period +
                ", batchDate='" + batchDate + '\'' +
                ", userId='" + userId + '\'' +
                ", ledger='" + batchledger + '\'' +
                ", currency='" + batchCurrency + '\'' +
                '}';
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Double getYearNo() {
        return yearNo;
    }

    public void setYearNo(Double yearNo) {
        this.yearNo = yearNo;
    }

    public Double getPeriod() {
        return period;
    }

    public void setPeriod(Double period) {
        this.period = period;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBatchledger() {
        return batchledger;
    }

    public void setBatchledger(String batchledger) {
        this.batchledger = batchledger;
    }

    public String getBatchCurrency() {
        return batchCurrency;
    }

    public void setBatchCurrency(String batchCurrency) {
        this.batchCurrency = batchCurrency;
    }
}
