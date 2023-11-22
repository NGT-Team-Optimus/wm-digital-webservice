package com.cg.teamoptimus.WealthManagement.model;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="goals")
public class Goal {
	@Id
	private int goalId;
	private String goalName;
	private Date duration;
	private Long financialGoalValue;
	private String goalTerm;
	private boolean isFirstTimeDurationSet = true;

	public Goal(int goalId, String goalName, Date duration,
			Long financialGoalValue,String goalTerm) {
		super();
		this.goalId = goalId; 
		this.goalName = goalName;
		this.duration = duration;
		this.financialGoalValue=financialGoalValue;
		this.goalTerm=goalTerm;
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
		if (isFirstTimeDurationSet && duration != null) {
			this.duration = duration;
			setGoalTerm();
			isFirstTimeDurationSet = false;
		} else {
			this.duration = duration;
		}

	}
	
	public Long getFinancialGoalValue() {
		return financialGoalValue;
	}
	public void setFinancialGoalValue(Long financialGoalValue) {
		this.financialGoalValue = financialGoalValue;
	}

	public String getGoalTerm() {
		return goalTerm;
	}

	private void setGoalTerm() {
		if (duration != null) {
			Date currentDate = new Date();
			long durationInMillis = duration.getTime() - currentDate.getTime();
			long months = Duration.ofMillis(durationInMillis).toDays() / 30;
			if (months < 3) {
				this.goalTerm = "Short Term";
			} else if (months >= 3 && months < 12) {
				this.goalTerm = "Mid Term";
			} else {
				this.goalTerm = "Long Term";
			}
		} else {
			this.goalTerm = "null";
		}
	}
	@Override
	public String toString() {
		return "Goal [goalId=" + goalId + ", goalName=" + goalName + ", duration=" + duration + ", financialGoalValue="
				+ financialGoalValue + "]";
	}
	
	
}
