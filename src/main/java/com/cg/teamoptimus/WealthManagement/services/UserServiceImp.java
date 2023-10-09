package com.cg.teamoptimus.WealthManagement.services;


import java.util.List;

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
	public String register(JwtRequest user) {
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
	public String updateUserDetails(JwtRequest user) {
		//JwtRequest existingUser = userRepo.findByEmail(user.getEmail());

		if (userRepo.existsByUserId(user.getUserId())) {
			userRepo.save(user);
			logger.info("Updated succesfully");
			return "Updated Successfully";
		} else {
			logger.info("No User found");
			return "No User Found";
		}
	}

	@Override
	public String loginUser(JwtRequest user) {
		JwtRequest result=userRepo.findByEmail(user.getEmail());
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
	
	

}
