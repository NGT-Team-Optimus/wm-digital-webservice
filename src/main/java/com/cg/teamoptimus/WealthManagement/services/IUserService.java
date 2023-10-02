package com.cg.teamoptimus.WealthManagement.services;

import java.util.List;

import com.cg.teamoptimus.WealthManagement.model.User;

public interface IUserService{
	
	public String register(User user);
	
	
	public String updateUserDetails(User user);
	
	public List<User> getAllUsers();
	
	public String loginUser(User user);
	

}
