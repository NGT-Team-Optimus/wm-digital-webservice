package com.cg.teamoptimus.WealthManagement.services;


import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

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

	Random random = new Random();

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
	public User getUserByUserId(UUID userId) {
		return userRepo.findByUserId(userId);
	}
	@Override
	public String forgotPassword(String email){
		if(userRepo.existsByEmail(email)){
			String token = String.format("%04d", random.nextInt(10000));
			return token;
		}
		return "User Not Forund";
	}
	

}
