package com.cg.teamoptimus.WealthManagement.services;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import com.cg.teamoptimus.WealthManagement.model.User;

public interface IUserService{
	
	public String register(User user);
	public String updateUserDetails(User user);
	public String loginUser(JwtRequest jwtRequest);
	

}
