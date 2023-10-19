package com.cg.teamoptimus.WealthManagement.services;

import java.util.List;
import java.util.UUID;

import com.cg.teamoptimus.WealthManagement.model.Goal;

public interface IGoalService {
	
	public List<Goal> getAllGoals();
	public List<Goal> addGoalsForUser(UUID userId,List<Goal> goals);

}
