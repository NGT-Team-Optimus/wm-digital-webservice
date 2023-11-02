package com.cg.teamoptimus.WealthManagement.services;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import com.cg.teamoptimus.WealthManagement.model.User;

import java.util.UUID;

public interface IUserService{
	
	public UUID register(User user);
	User getUserByUserId(UUID userId);
	String forgotPassword(String email);
}
