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
    private String fundId;
    private double openingBalance;
    private double amount;
    private Date transactionDate;
    private String transactionType;
    private double closingBalance;

    public UserFund() {
    }

    public UserFund(UUID userId, String fundId, double openingBalance, double amount, Date transactionDate,
                    String transactionType, double closingBalance) {
        this.userId = userId;
        this.fundId = fundId;
        this.openingBalance = openingBalance;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.closingBalance = closingBalance;
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

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

 // Update the transactionDate with the current system date if not explicitly set
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

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        this.closingBalance = closingBalance;
    }
    
 // Calculate the closing balance based on transactions
    private double calculateClosingBalance() {
        return openingBalance + amount;
    }


	@Override
	public String toString() {
		return "UserFund [id=" + id + ", userId=" + userId + ", fundId=" + fundId + ", openingBalance=" + openingBalance
				+ ", amount=" + amount + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType
				+ ", closingBalance=" + closingBalance + "]";
	}

    

}
