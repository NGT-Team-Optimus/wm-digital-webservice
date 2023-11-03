package com.cg.teamoptimus.WealthManagement.services;


import java.util.*;
import com.cg.teamoptimus.WealthManagement.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.teamoptimus.WealthManagement.repository.IUserRepository;

@Service
public class UserServiceImp implements IUserService {
	

	@Autowired
	private IUserRepository userRepo;
	Logger logger = LoggerFactory.getLogger(IUserService.class);

	Random random = new Random();
	private Map<String, String> tokenStorage = new HashMap<>();

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
			// Store the token in the map associated with the email
			tokenStorage.put(email, token);
			return token;
		}
		return "User Not Forund";
	}
	@Override
	public String confirmPassword(String email, String code, String newPassword) {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			String token = tokenStorage.get(email); // Retrieve the token from the map
			if (code.equals(token)) {
				user.setPassword(newPassword);
				userRepo.save(user);
				tokenStorage.remove(email,token);
				return "Password has changed successfully";
			}
			return "Invalid Code";
		}
		return "User Not Found";
	}

}
