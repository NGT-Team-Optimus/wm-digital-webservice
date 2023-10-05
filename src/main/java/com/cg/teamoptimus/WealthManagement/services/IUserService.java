package com.cg.teamoptimus.WealthManagement.services;

import java.util.List;

import com.cg.teamoptimus.WealthManagement.model.JwtRequest;

public interface IUserService{
	
	public String register(JwtRequest user);
	
	
	public String updateUserDetails(JwtRequest user);
	
	public List<JwtRequest> getAllUsers();
	
	public String loginUser(JwtRequest user);
	

}
