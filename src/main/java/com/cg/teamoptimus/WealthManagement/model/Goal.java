package com.cg.teamoptimus.WealthManagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="goals")
public class Goal {
	@Id
	private int goalId;
	private String goalName;
	private String goalStatus;
	private Date duration;
	private Long financialGoalValue;
	public Goal(int goalId, String goalName, String goalStatus, Date duration,
			Long financialGoalValue) {
		super();
		this.goalId = goalId;
		this.goalName = goalName;
		this.goalStatus = goalStatus;
		this.duration = duration;
		this.financialGoalValue=financialGoalValue;
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
	public String getGoalStatus() {
		return goalStatus;
	}
	public void setGoalStatus(String goalStatus) {
		this.goalStatus = goalStatus;
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
}
