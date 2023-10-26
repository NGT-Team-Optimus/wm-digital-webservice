package com.cg.teamoptimus.WealthManagement.services;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.cg.teamoptimus.WealthManagement.helper.JwtUtil;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import com.cg.teamoptimus.WealthManagement.model.JwtResponse;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.repository.IGoalRepository;
@Service
public class GoalServiceImp implements IGoalService {
	@Autowired
	IGoalRepository goalRepo;
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IUserService userService;

	Logger logger = LoggerFactory.getLogger(IUserService.class);

	@Override
	public List<Goal> getAllGoals() {
		
		List<Goal> goals= goalRepo.findAll();
		return goals;
	}

	@Override
	public List<Goal> addGoalsForUser(UUID userId, List<Goal> goals) {
		User user = userService.getUserByUserId(userId);
		if (user == null) {
			// Handle the case where the user doesn't exist
			logger.info("user not found");
			return Collections.emptyList();
		}
		for (Goal goal : goals) {
			goal.setUser(user);
		}
		return goalRepo.saveAll(goals);
	}






}
