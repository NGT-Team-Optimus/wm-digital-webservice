package com.cg.teamoptimus.WealthManagement.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.teamoptimus.WealthManagement.entity.User;
import com.cg.teamoptimus.WealthManagement.repository.IUserRepository;
@Service
public class UserServiceImp implements IUserService {
	

	@Autowired
	private IUserRepository userRepo;
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
	public String signin(String email,String password) {
		String result = userRepo.findPasswordByEmail(email);
		if (result != null) {
            if (result.equals(password)) {
                logger.info("Successfully logged in "+result);
                return "Successfully Logged In";
            } else {
                logger.info("Invalid password"+result);
                return "Invalid password";
           }
        } else {
            logger.info("User does not exist"+result);
            return "User does not exist";
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
	
	

}
