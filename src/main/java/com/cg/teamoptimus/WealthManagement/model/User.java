package com.cg.teamoptimus.WealthManagement.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;


@Document(collection="users")
public class User {
	
	@Id
	private int userId;
	private String username;
	@NotEmpty(message="email should not be empty")
	private String email;
	@NotEmpty(message="password should not be empty")
	private String password;
	private String userSSN;
	private String status;
	@DBRef
	private List<Goal> goals;
	
	public int getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public void setUserId(int userId) {
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
		public User() {
		super();
		// TODO Auto-generated constructor stub
	}
		public User(int userId, String username, @NotEmpty(message = "email should not be empty") String email,
				@NotEmpty(message = "password should not be empty") String password, String userSSN, String status,
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
