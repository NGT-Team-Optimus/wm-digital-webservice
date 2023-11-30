package com.cg.teamoptimus.WealthManagement.model;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification")
public class Notification {

	@Id
    private String id;
	
	private UUID userId;
	private String userName;
	
	private int goalId;
	private String goalName;
	
    private String timeline;
    
    private String status= "GOALTIMELINE";
    
    public Notification() {
        // Default constructor
    }
    
	public Notification(String id, UUID userId, String userName, int goalId, String goalName, String timeline,
			String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.goalId = goalId;
		this.goalName = goalName;
		this.timeline = timeline;
		this.status = status;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	
    
}
