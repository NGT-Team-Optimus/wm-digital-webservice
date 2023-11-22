package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IUserGoalService {
	
    public UserGoal addGoalsByUser(UserGoal userGoal);
    
    public Goal getGoalByUserIdAndGoalId(UUID userId, int goalId);
    
    public UserGoal updateGoalDetails(UUID userId, int goalId, Date duration, Long financialGoalValue);
    
    public Goal getGoalDetails(UUID userId, int goalId);
    
    public UserGoal getUserGoal(UUID userId);
    
    public Goal updateOneGoalDetails(UUID userId, int goalId, Date duration, Long financialGoalValue);

    public int getGoalCountByUserId(UUID userId);
    public List<Goal> getShortTermGoalsByUser(UUID userId);
    public List<Goal> getMidTermGoalsByUser(UUID userId);
    public List<Goal> getLongTermGoalsByUser(UUID userId);


}