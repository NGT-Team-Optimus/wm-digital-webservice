package com.cg.teamoptimus.WealthManagement.services;

import java.util.List;

import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.repository.IGoalRepository;
@Service
public class GoalServiceImp implements IGoalService {
	@Autowired
	IGoalRepository goalRepo;

	@Override
	public List<Goal> getAllGoals() {
		
		List<Goal> goals= goalRepo.findAll();
		return goals;
	}




}
