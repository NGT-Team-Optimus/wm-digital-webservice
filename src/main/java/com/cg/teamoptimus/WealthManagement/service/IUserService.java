package com.cg.teamoptimus.WealthManagement.service;

import com.cg.teamoptimus.WealthManagement.entity.User;

public interface IUserService {
	
	public String register(User user);
	
	public String signin(String email,String password);
	
	public String updateUserDetails(User user);

}
