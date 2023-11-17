package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.Transaction;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;
import com.cg.teamoptimus.WealthManagement.repository.IGoalRepository;
import com.cg.teamoptimus.WealthManagement.repository.IUserGoalRepository;

import ch.qos.logback.core.status.Status;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
    	System.out.println(userGoal);        

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
//            return "UserGoal not found"; // Handle this case as needed
        	return null;
        }
        System.out.println(userGoal.getUser().getUsername());

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
    public List<Transaction> getAllTransactionsForUserGoal(UUID userId) {
        UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
        if (userGoal != null) {
            List<Transaction> transactions = new ArrayList<>();
            for (Goal goal : userGoal.getGoals()) {
                transactions.addAll(goal.getTransactions());
            }
            return transactions;
        }
        return List.of();
	}


    @Override
    public int getGoalCountByUserId(UUID userId) {
        UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
        if (userGoal != null) {
            int noOfGoals=userGoal.getNumberOfGoals();
            // Return the count of goals for the user
            return noOfGoals;
        }
        return 0; // If no UserGoal found, return 0 goals

    }
	
	 @Override
	    public Transaction addTransactionToGoal(UUID userId, int goalId, Transaction transaction) {
	        UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
	        
	        if (userGoal == null) {
	            return null;
	        }

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

	        List<Transaction> goalTransactions = goalToUpdate.getTransactions();
	        if (goalTransactions == null) {
	            goalTransactions = new ArrayList<>();
	        }
	        
	     // Check for duplicate transaction IDs
	        boolean isDuplicate = goalTransactions.stream()
	        	    .anyMatch(existingTransaction -> existingTransaction.getTransactionId() == transaction.getTransactionId());
	        if (isDuplicate) {
	        	System.out.println("Same Transaction ID");
	            return null;
	        }
	        
	        goalTransactions.add(transaction);
            goalToUpdate.setTransactions(goalTransactions);
            Long totalAmount = calculateTotalAmount(goalTransactions);
            goalToUpdate.setTotalAmount(totalAmount);
            
            
            if (goalToUpdate.getFinancialGoalValue() != null) {
                if (totalAmount >= goalToUpdate.getFinancialGoalValue()) {
                	goalToUpdate.setStatus("Completed");
                } else {
                	goalToUpdate.setStatus("Active");
                }
            }

            userGoalRepo.save(userGoal);

            return transaction;
	    }
	 
	 @Override
	    public List<Transaction> getAllTransactionsForGoal(UUID userId, int goalId) {
	        UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
	        if (userGoal != null) {
	            // Find the goal within the UserGoal by goalId
	            for (Goal goal : userGoal.getGoals()) {
	                if (goal.getGoalId() == goalId) {
	                	
	                	 List<Transaction> transactions = goal.getTransactions();
	                	 if (transactions != null) {
	                	 Long totalAmount = calculateTotalAmount(transactions);
	                     updateTotalAmount(userId, goalId, totalAmount);
	                     return transactions;
	                	 }else {
	                		 return new ArrayList<>();
	                	 }
	                }
	            }
	        }
	        return null; 
	    }
	 
	 private Long calculateTotalAmount(List<Transaction> transactions) {
	        return transactions.stream()
	                .mapToLong(Transaction::getAmount)
	                .sum();
	    }
	 
	 private void updateTotalAmount(UUID userId, int goalId, Long totalAmount) {
	        UserGoal userGoal = userGoalRepo.findByUserUserId(userId);
	        if (userGoal != null) {
	            for (Goal goal : userGoal.getGoals()) {
	                if (goal.getGoalId() == goalId) {
	                    goal.setTotalAmount(totalAmount);
	                    System.out.println(totalAmount);
	                    if (goal.getFinancialGoalValue() != null) {
	                        if (totalAmount >= goal.getFinancialGoalValue()) {
	                            goal.setStatus("Completed");
	                        } else {
	                            goal.setStatus("Active");
	                        }
	                    }
	                    System.out.println(goal.getStatus());
	                    userGoalRepo.save(userGoal);
	                    break;
	                }
	            }
	        }
	    }
	}