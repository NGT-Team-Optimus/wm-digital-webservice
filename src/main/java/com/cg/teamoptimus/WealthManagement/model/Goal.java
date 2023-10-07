package com.cg.teamoptimus.WealthManagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="goals")
public class Goal {
	@Id
	@Field("_id")
	private String goalId;
	private String goalName;
	private String goalStatus;
	private Date duration;
	private Long financialGoalValue;
	public Goal(String goalId, String goalName, String goalStatus, Date duration,
			Long financialGoalValue) {
		super();
		this.goalId = goalId;
		this.goalName = goalName;
		this.goalStatus = goalStatus;
		this.duration = duration;
		this.financialGoalValue=financialGoalValue;
	}
	public String getGoalId() {
		return goalId;
	}
	public void setGoalId(String goalId) {
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
