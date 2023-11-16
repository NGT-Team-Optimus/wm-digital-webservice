package com.cg.teamoptimus.WealthManagement.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.cg.teamoptimus.WealthManagement.model.Goal;

public interface IGoalService {
	
	public List<Goal> getAllGoals();
	public Goal getGoalByGoalId(int goalId);
	public Goal updateOneGoalDetail(UUID userId, int goalId, Date duration, Long financialGoalValue);

}
