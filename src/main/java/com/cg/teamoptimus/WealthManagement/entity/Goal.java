package com.cg.teamoptimus.WealthManagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="goals")
public class Goal {
	@Id
	private int goalId;
	private String goalName;
	private String goalStatus;
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
	@Override
	public String toString() {
		return "Goal [goalId=" + goalId + ", goalName=" + goalName + ", goalStatus=" + goalStatus + "]";
	}
	public Goal(int goalId, String goalName, String goalStatus) {
		super();
		this.goalId = goalId;
		this.goalName = goalName;
		this.goalStatus = goalStatus;
	}
	

}
