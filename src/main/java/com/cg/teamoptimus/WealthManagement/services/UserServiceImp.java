package com.cg.teamoptimus.WealthManagement.services;


import java.util.List;
import java.util.UUID;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import com.cg.teamoptimus.WealthManagement.repository.IUserRepository;

@Service
public class UserServiceImp implements IUserService {
	

	@Autowired
	private IUserRepository userRepo;
	Logger logger = LoggerFactory.getLogger(IUserService.class);

	@Override
	public UUID register(User user) {
		if(userRepo.existsByEmail(user.getEmail())) {
			logger.info("User Already exists");
			return null;
		}
		else {
			User result=userRepo.save(user);
			logger.info("Registered successsfully");
			return result.getUserId();
		}
	}

	@Override
	public String updateUserDetails(User user) {
		if (userRepo.existsByEmail(user.getEmail())) {
			userRepo.save(user);
			logger.info("Updated succesfully");
			return "Updated Successfully";
		} else {
			logger.info("No User found");
			return "No User Found";
		}
	}

	@Override
	public User getUserByUserId(UUID userId) {
		return userRepo.findByUserId(userId);
	}
	

}
