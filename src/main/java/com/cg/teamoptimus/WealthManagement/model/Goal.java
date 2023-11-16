package com.cg.teamoptimus.WealthManagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="goals")
public class Goal {
	@Id
	private int goalId;
	private String goalName;
	private Date duration;
	private Long financialGoalValue;
	
	private List<Long> transactionAmount;//Transaction
	private Long totalTransactionAmount;
	
    public Goal(int goalId, String goalName, Date duration, Long financialGoalValue, List<Long> transactionAmount) {
		super();
		this.goalId = goalId;
		this.goalName = goalName;
		this.duration = duration;
		this.financialGoalValue = financialGoalValue;
		this.transactionAmount = transactionAmount;
        this.totalTransactionAmount = calculateTotalTransactionAmount();

	}
	
	public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	public Date getDuration() {
		return duration;
	}
	public void setDuration(Date duration) {
		this.duration = duration;
	}
	
	public Long getFinancialGoalValue() {
		return financialGoalValue;
	}
	public void setFinancialGoalValue(Long financialGoalValue) {
		this.financialGoalValue = financialGoalValue;
	}
	
	public List<Long> getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(List<Long> transactionAmount) {
        this.transactionAmount = transactionAmount;
        this.totalTransactionAmount = calculateTotalTransactionAmount();
    }
	
	public Long getTotalTransactionAmount() {
        return totalTransactionAmount;
    }
	
	private Long calculateTotalTransactionAmount() {
        long total = 0L;

        if (transactionAmount != null) {
            for (Long transaction : transactionAmount) {
                total += transaction;
            }
        }

        return total;
    }
	 
	 public void addTransaction(Long transaction) {
	        if (transactionAmount == null) {
	            transactionAmount = new ArrayList<>();
	        }
	        transactionAmount.add(transaction);
	        this.totalTransactionAmount = calculateTotalTransactionAmount();

	    }


	 @Override
	    public String toString() {
	        return "Goal [goalId=" + goalId + ", goalName=" + goalName + ", duration=" + duration + ", financialGoalValue="
	                + financialGoalValue + ", transactionAmount=" + transactionAmount + ", totalTransactionAmount="
	                + totalTransactionAmount + "]";
	    }

	
}
