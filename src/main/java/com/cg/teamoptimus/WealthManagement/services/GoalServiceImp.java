package com.cg.teamoptimus.WealthManagement.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cg.teamoptimus.WealthManagement.helper.JwtUtil;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import com.cg.teamoptimus.WealthManagement.model.JwtResponse;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;
import com.cg.teamoptimus.WealthManagement.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.repository.IGoalRepository;
import com.cg.teamoptimus.WealthManagement.repository.IUserGoalRepository;
@Service
public class GoalServiceImp implements IGoalService {
	@Autowired
	IGoalRepository goalRepo;
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IUserGoalRepository userGoalRepo;
	@Autowired
	IUserService userService;
	@Autowired
	IGoalService goalService;

	Logger logger = LoggerFactory.getLogger(IUserService.class);

	@Override
	public List<Goal> getAllGoals() {
		
		List<Goal> goals= goalRepo.findAll();
		return goals;
	}

	@Override
	public Goal getGoalByGoalId(int goalId) {
		return goalRepo.findByGoalId(goalId);
	}
	
	
	
	
	
	
	
	@Override
    public Goal updateOneGoalDetail(UUID userId, int goalId, Date duration, Long financialGoalValue) {
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
//        goalRepo.save(goalToUpdate);
        
//        System.out.println(userGoal);
        
        return goalRepo.save(goalToUpdate);
    }


}
