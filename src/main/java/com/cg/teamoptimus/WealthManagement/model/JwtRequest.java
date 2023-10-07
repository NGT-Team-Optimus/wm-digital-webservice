package com.cg.teamoptimus.WealthManagement.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//import jakarta.validation.constraints.NotEmpty;


@Document(collection="users")
public class JwtRequest {
	
	@Id
	@Field("_id")
	private String userId;
	private String username;
	//@NotEmpty(message="email should not be empty")
	private String email;
	//@NotEmpty(message="password should not be empty")
	private String password;
	private String userSSN;
	private boolean status;
	
	private List<Goal> goals;
	
	public String getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserSSN() {
		return userSSN;
	}
	public void setUserSSN(String userSSN) {
		this.userSSN = userSSN;
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
		public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
		public JwtRequest(String userId, String username, String email,
				 String password, String userSSN, boolean status,
				List<Goal> goals) {
			super();
			this.userId = userId;
			this.username = username;
			this.email = email;
			this.password = password;
			this.userSSN = userSSN;
			this.status = status;
			this.goals = goals;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + username + ", email=" + email + ", password=" + password
					+ ", userSSN=" + userSSN + ", status=" + status + ", goals=" + goals + "]";
		}
	
	
	

}