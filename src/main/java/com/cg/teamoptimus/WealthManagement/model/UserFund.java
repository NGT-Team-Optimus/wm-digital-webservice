package com.cg.teamoptimus.WealthManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "user_funds")
public class UserFund {
    @Id
    private String id;

    private UUID userId;
    private int fundId;
    private String fundName;
    private double openingBalance;
    private double amount;
    private Date transactionDate;
    private String transactionType;
    private double closingBalance;

    public UserFund() {
    }

    public UserFund(UUID userId, int fundId, double openingBalance, double amount, Date transactionDate,
                    String transactionType, double closingBalance,String fundName) {
        this.userId = userId;
        this.fundId = fundId;
        this.fundName=fundName;
        this.openingBalance = openingBalance;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.closingBalance = closingBalance;
        recalculateClosingBalance(); // Initialize or update closing balance during object creation
    }

    // Getters and setters for class fields

    // ...

    public void setAmount(double amount) {
        this.amount = amount;
        recalculateClosingBalance();
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        recalculateClosingBalance();
    }

    private void recalculateClosingBalance() {
        if (transactionType != null) {
            if (transactionType.equalsIgnoreCase("deposit")) {
                this.closingBalance = openingBalance + amount;
            } else if ((transactionType.equalsIgnoreCase("withdraw"))|| (transactionType.equalsIgnoreCase("transferToGoal"))) {
                this.closingBalance = openingBalance - amount;
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getFundId() {
        return fundId;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
    public void setFundId(int fundId) {
        this.fundId=fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        if (transactionDate == null) {
            this.transactionDate = new Date(); // Set to system's current date
        } else {
            this.transactionDate = transactionDate; // Use provided date if not null
        }
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        this.closingBalance = closingBalance;
    }

    @Override
    public String toString() {
        return "UserFund{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", fundId='" + fundId + '\'' +
                ", openingBalance=" + openingBalance +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", transactionType='" + transactionType + '\'' +
                ", closingBalance=" + closingBalance +
                '}';
    }
}
