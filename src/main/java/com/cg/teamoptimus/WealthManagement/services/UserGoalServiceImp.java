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

        if (!userGoalRepo.existsByUserUserId(userGoal.getUser().getUserId())) {
            User user = userService.getUserByUserId(userGoal.getUser().getUserId());
            userGoal.setUser(user);
            for (Goal goal : userGoal.getGoals()) {
                Goal goal1 = goalService.getGoalByGoalId(goal.getGoalId());
                goal.setGoalName(goal1.getGoalName());
                goal.setFinancialGoalValue(null);
                goal.setDuration(null);
            }
            return userGoalRepo.save(userGoal);

        }
        logger.info("User already exists");
        return null;
    }
    
    
    @Override
    public Goal getGoalByUserIdAndGoalId(UUID userId, int goalId) {
        return userGoalRepo.findGoalByUserUserIdAndGoalsGoalId(userId, goalId);
    }
 

    @Override
    public UserGoal updateGoalDetails(UUID userId, int goalId, Date duration, Long financialGoalValue) {
    	UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
    	
    	System.out.println(userGoal);        

        if (userGoal == null) {
//            return "UserGoal not found"; // Handle this case as needed
        	return null;
        }

        // Find the goal within the UserGoal by goalId
        Goal goalToUpdate = null;
        for (Goal goal : userGoal.getGoals()) {
            if (goal.getGoalId() == goalId) {
                goalToUpdate = goal;
                
                
                if (goalToUpdate != null) {
//                  return "Goal not found for the specified goalId"; // Handle this case as needed
                goalToUpdate.setDuration(duration);
                goalToUpdate.setFinancialGoalValue(financialGoalValue);
  
                }
                
            	System.out.println(goalToUpdate);
                break;
            }
        }

        if (goalToUpdate == null) {
//            return "Goal not found for the specified goalId"; // Handle this case as needed
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
//            return "UserGoal not found"; // Handle this case as needed
        	return null;
        }
        System.out.println(userGoal.getUser().getUsername());

        // Find the goal within the UserGoal by goalId
        Goal goalToUpdate = null;
        
        for (Goal goal : userGoal.getGoals()) {
            if (goal.getGoalId() == goalId) {
//            	System.out.println("----------------");
//            	System.out.println(goal);
//            	System.out.println("----------------");
                goalToUpdate = goal;
//                System.out.println("----------------");
//            	System.out.println(goalToUpdate);
//            	System.out.println("----------------");
                break;
            }
        }
        
        if (goalToUpdate == null) {
//            return "Goal not found for the specified goalId"; // Handle this case as needed
        	return null;
        }
//        System.out.println("----------------");
//    	System.out.println(goalToUpdate);
//    	System.out.println("----------------");
//        
//                
//        System.out.println(goalToUpdate.getGoalId());
//        System.out.println(goalToUpdate.getGoalName());
//        System.out.println(goalToUpdate.getDuration());
//        System.out.println(goalToUpdate.getFinancialGoalValue());
                
        return goalToUpdate;
	}
	
	
	
	
	
	@Override
    public Goal updateOneGoalDetails(UUID userId, int goalId, Date duration, Long financialGoalValue) {
    	UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
    	
    	System.out.println(userGoal);        

        if (userGoal == null) {
//            return "UserGoal not found"; // Handle this case as needed
        	return null;
        }

        // Find the goal within the UserGoal by goalId
        Goal goalToUpdate = null;
        for (Goal goal : userGoal.getGoals()) {
            if (goal.getGoalId() == goalId) {
                goalToUpdate = goal;
                break;
            }
        }

        if (goalToUpdate == null) {
//            return "Goal not found for the specified goalId"; // Handle this case as needed
            return null;
        }

        // Update the duration and financialGoalValue
        goalToUpdate.setDuration(duration);
        goalToUpdate.setFinancialGoalValue(financialGoalValue);
        
        // Save the updated UserGoal
//        userGoalRepo.save(userGoal);
        goalRepo.save(goalToUpdate);
        
        System.out.println(userGoal);
        
        return goalToUpdate;
    }
	
	
	
	
	
	
}