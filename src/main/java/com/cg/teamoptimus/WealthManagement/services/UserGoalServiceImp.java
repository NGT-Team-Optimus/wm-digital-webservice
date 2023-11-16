package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;
import com.cg.teamoptimus.WealthManagement.repository.IGoalRepository;
import com.cg.teamoptimus.WealthManagement.repository.IUserGoalRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class UserGoalServiceImp implements IUserGoalService {

    Logger logger = LoggerFactory.getLogger(IUserGoalService.class);
    @Autowired
    IUserGoalRepository userGoalRepo;
    @Autowired
    IGoalRepository goalRepo;
    @Autowired
    IGoalService goalService;
    @Autowired
    IUserService userService;
    @Override
    public UserGoal addGoalsByUser(@NotNull UserGoal userGoal) {
        User user = userService.getUserByUserId(userGoal.getUser().getUserId());
        if (user == null) {
            logger.info("User not found");
            return null;
        }
        // Check if a UserGoal already exists for the user
        UserGoal existingUserGoal = userGoalRepo.findByUserUserId(user.getUserId());
        if (existingUserGoal != null) {
            // Check if any of the new goals already exist in the existing UserGoal
            List<Goal> newGoals = userGoal.getGoals();
            List<Goal> existingGoals = existingUserGoal.getGoals();
            for (Goal newGoal : newGoals) {
                if (existingGoals.stream().anyMatch(existingGoal -> existingGoal.getGoalId() == newGoal.getGoalId())) {
                    logger.info("Goal already exists");
                    return null; // Goal already exists, return null or handle as needed
                }
                Goal goal = goalService.getGoalByGoalId(newGoal.getGoalId());
                newGoal.setGoalName(goal.getGoalName());
                goal.setFinancialGoalValue(null);
                goal.setDuration(null);
            }
            // If none of the new goals already exist, add them
            existingGoals.addAll(newGoals);
            // Save the updated UserGoal
            return userGoalRepo.save(existingUserGoal);
        } else {
            // Create a new UserGoal and save it
            userGoal.setUser(user);
            for (Goal goal : userGoal.getGoals()) {
                Goal goal1 = goalService.getGoalByGoalId(goal.getGoalId());
                goal.setGoalName(goal1.getGoalName());
                goal.setFinancialGoalValue(null);
                goal.setDuration(null);
//                goal.set();
            }
            return userGoalRepo.save(userGoal);
        }
    }
    
    @Override
    public Goal getGoalByUserIdAndGoalId(UUID userId, int goalId) {
        return userGoalRepo.findGoalByUserUserIdAndGoalsGoalId(userId, goalId);
    }
 

    @Override
    public UserGoal updateGoalDetails(UUID userId, int goalId, Date duration, Long financialGoalValue) {
    	UserGoal userGoal = userGoalRepo.findByUserUserId(userId);


        if (userGoal == null) {
        	return null;
        }

        // Find the goal within the UserGoal by goalId
        Goal goalToUpdate = null;
        for (Goal goal : userGoal.getGoals()) {
            if (goal.getGoalId() == goalId) {
                goalToUpdate = goal;
                
                
                if (goalToUpdate != null) {
                goalToUpdate.setDuration(duration);
                goalToUpdate.setFinancialGoalValue(financialGoalValue);
  
                }
                
            	System.out.println(goalToUpdate);
                break;
            }
        }

        if (goalToUpdate == null) {
            return null;
        }

        // Update the duration and financialGoalValue
        goalToUpdate.setDuration(duration);
        goalToUpdate.setFinancialGoalValue(financialGoalValue);
        
        // Save the updated UserGoal
        
        System.out.println(userGoal);
        
        return userGoalRepo.save(userGoal);
    }


	@Override
	public UserGoal getUserGoal(UUID userId) {

		return userGoalRepo.findByUserUserId(userId);

	}


	@Override
	public Goal getGoalDetails(UUID userId, int goalId) {
		
		UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
    	
        if (userGoal == null) {
        	return null;
        }
        System.out.println(userGoal.getUser().getUsername());

        // Find the goal within the UserGoal by goalId
        Goal goalToUpdate = null;
        
        for (Goal goal : userGoal.getGoals()) {
            if (goal.getGoalId() == goalId) {
                goalToUpdate = goal;
                break;
            }
        }
        
        if (goalToUpdate == null) {
        	return null;
        }
                
        return goalToUpdate;
	}

	@Override
	public int getGoalCountByUserId(UUID userId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	@Override
	public Goal addTransactionToGoal(UUID userId, int goalId,Long TranAmount) {
		
		UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
		if (userGoal == null) {
        	return null;
        }
		
		// Find the goal within the UserGoal by goalId
        Goal goalToUpdate = null;
        for (Goal goal : userGoal.getGoals()) {
            if (goal.getGoalId() == goalId) {
                goalToUpdate = goal;
                
                
                if (goalToUpdate != null) {
                	goalToUpdate.addTransaction(TranAmount);
                }
                
            	System.out.println(goalToUpdate);
                break;
            }
        }

        if (goalToUpdate == null) {
            return null;
        }
        
        userGoalRepo.save(userGoal);
        
        return goalToUpdate;
		
	}

	
}