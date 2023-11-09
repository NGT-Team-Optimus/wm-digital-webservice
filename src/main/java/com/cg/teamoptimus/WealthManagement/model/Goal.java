package com.cg.teamoptimus.WealthManagement.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="goals")
public class Goal {
	@Id
	private int goalId;
	private String goalName;
	private Date duration;
	private Long financialGoalValue;
	private List<Long> transactionAmount;//Transaction

	
	public Goal(int goalId, String goalName, Date duration, Long financialGoalValue, List<Long> transactionAmount) {
		super();
		this.goalId = goalId;
		this.goalName = goalName;
		this.duration = duration;
		this.financialGoalValue = financialGoalValue;
		this.transactionAmount = transactionAmount;
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
	}

	@Override
	public String toString() {
		return "Goal [goalId=" + goalId + ", goalName=" + goalName + ", duration=" + duration + ", financialGoalValue="
				+ financialGoalValue + ", transactionAmount=" + transactionAmount + "]";
	}


	
}
