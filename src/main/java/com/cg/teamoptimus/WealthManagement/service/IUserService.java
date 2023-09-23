package com.cg.teamoptimus.WealthManagement.service;

import java.util.List;

import com.cg.teamoptimus.WealthManagement.entity.User;

public interface IUserService {
	
	public String register(User user);
	
	
	public String updateUserDetails(User user);
	
	public List<User> getAllUsers();
	
	public String loginUser(User user);
	

}
