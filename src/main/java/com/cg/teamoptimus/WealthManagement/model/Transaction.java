package com.cg.teamoptimus.WealthManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document(collection = "transactions")
public class Transaction {
	@Id
    private String transactionId;
    private Date transactionDate;
    private Long amount;
	private int fundId;


    public Transaction(Date transactionDate, Long amount,int fundId) {

        if (transactionDate == null) {
            this.transactionDate = new Date(); // Initialize with the current date if null is provided
        } else {
            this.transactionDate = transactionDate; // Use provided transactionDate
        }
        this.amount = amount;
		this.fundId=fundId;
    }

	public Transaction() {

	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public int getFundId() {
		return fundId;
	}

	public void setFundId(int fundId) {
		this.fundId = fundId;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transactionId='" + transactionId + '\'' +
				", transactionDate=" + transactionDate +
				", amount=" + amount +
				", fundId=" + fundId +
				'}';
	}
}
