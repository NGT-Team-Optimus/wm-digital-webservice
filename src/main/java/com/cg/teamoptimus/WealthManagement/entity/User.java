package com.cg.teamoptimus.WealthManagement.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;


@Document(collection="users")
public class User {
	
	@Id
	private int userId;
	private String userName;
	@NotEmpty(message="email should not be empty")
	private String email;
	@NotEmpty(message="password should not be empty")
	private String password;
	private String userSSN;
	private String status;
	
	
	private List<Goal> goals;
	
	public int getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public User(int userId,String userName, String email, String password,String userSSN,String status) {
		super();
		this.userId = userId;
		this.userName=userName;
		this.email = email;
		this.password = password;
		this.userSSN=userSSN;
		this.status=status;
		
	}
	
	

}
