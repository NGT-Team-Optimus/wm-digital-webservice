package com.cg.teamoptimus.WealthManagement.services;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.repository.IGoalRepository;
import com.cg.teamoptimus.WealthManagement.repository.IUserRepository;
@Service
public class UserServiceImp implements IUserService {
	

	@Autowired
	private IUserRepository userRepo;
	private IGoalRepository goalRepo;
	Logger logger = LoggerFactory.getLogger(IUserService.class);

	@Override
	public String register(User user) {
		if(userRepo.existsByEmail(user.getEmail())) {
			logger.info("User Already exists");
			return "user already exists";
		}
		else {
			userRepo.save(user);
			logger.info("Registered successsfully");
			return "Registerd successfully";
		}
	}
	


	@Override
	public String updateUserDetails(User user) {
		if(userRepo.existsByuserId(user.getUserId())) {
			userRepo.save(user);
			return "Updated Successfully";
		}
		else {
			logger.info("No User found");
			return "No User Found";
		}
	}
	@Override
	public List<User> getAllUsers() {
		List<User> users=userRepo.findAll();
		logger.info("Success");
		return users;
	}
	@Override
	public String loginUser(User user) {
		User result=userRepo.findByEmail(user.getEmail());
		if(result!=null) {
			if((result.getPassword()).equals(user.getPassword())) {
				logger.info("Successfully logged in");
				return "success";
			}
			else {
				logger.info("invalid password");
				return "invalid password";
			}
		}
		else {
			logger.info("No User Found");
			return "No User Found";
		}
		
	}
	
	/*public User getUsers(int userId) {
		Optional<Goal> users=goalRepo.findById(userId);
		return users;
	}*/
	

}
