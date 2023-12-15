package com.cg.teamoptimus.WealthManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funds")
public class Fund {
    @Id
    private int fundId;
    private String fundName;
    private String fundCategory;
    private String fundDescription;
    private String fundStatus;

    public Fund(int fundId, String fundName, String fundCategory, String fundDescription, String fundStatus) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.fundCategory = fundCategory;
        this.fundDescription = fundDescription;
        this.fundStatus = fundStatus;
    }

    public Fund(){

    }

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public void setFundCategory(String fundCategory) {
        this.fundCategory = fundCategory;
    }

    public String getFundDescription() {
        return fundDescription;
    }

    public void setFundDescription(String fundDescription) {
        this.fundDescription = fundDescription;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }
}
