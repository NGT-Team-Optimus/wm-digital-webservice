package com.cg.teamoptimus.WealthManagement.model;

import java.util.Date;
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
	@DBRef
	private User user;
	//private UUID userId;

	public Goal(int goalId, String goalName, Date duration,
			Long financialGoalValue,User user) {
		super();
		this.goalId = goalId;
		this.goalName = goalName;
		this.duration = duration;
		this.financialGoalValue=financialGoalValue;
		this.user=user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
