package com.cg.teamoptimus.WealthManagement.controller;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;
import com.cg.teamoptimus.WealthManagement.repository.IGoalRepository;
import com.cg.teamoptimus.WealthManagement.services.IUserGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat; // Added import for DateTimeFormat

@RestController
public class UserGoalController {
    @Autowired
    IUserGoalService userGoalService;
    @Autowired
    IGoalRepository goalService;
   

    @PostMapping("/addGoals")
    public ResponseEntity<UserGoal> addUserGoals(@RequestBody UserGoal userGoal) {
        UserGoal createdUserGoal = userGoalService.addGoalsByUser(userGoal);
        if (createdUserGoal == null) {
            // Handle the case where the user doesn't exist
            return ResponseEntity.notFound().build();
        }
        // Return the created user-specific goals in the response
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserGoal);
    }

    @PutMapping("/updateGoalDetails/{userId}/{goalId}")
    public ResponseEntity<UserGoal> updateGoalDetails(
            @PathVariable UUID userId,
            @PathVariable int goalId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date duration,
            @RequestParam Long financialGoalValue) {
    	System.out.println(userId);
    	System.out.println(goalId);
    	System.out.println(duration);
    	System.out.println(financialGoalValue);
    	
        UserGoal result = userGoalService.updateGoalDetails(userId, goalId, duration, financialGoalValue);
        System.out.println(result);
        if (result==null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getGoalByUserIdAndGoalId/{userId}/{goalId}")
    public ResponseEntity<Goal> getGoalByUserIdAndGoalId(
            @PathVariable UUID userId,
            @PathVariable int goalId) {
        Goal goal = userGoalService.getGoalByUserIdAndGoalId(userId, goalId);
        
        if (goal == null) {
            // Handle the case where the goal is not found
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(goal);
    }
    
    @GetMapping("/getUserGoalByUserId/{userId}")
    public UserGoal getUserGoal( @PathVariable("userId") UUID userId) {
    	
    	return userGoalService.getUserGoal(userId);
    }
    
    
    
    @PutMapping("/getGoalDetails/{userId}/{goalId}")
    public ResponseEntity<Goal> updateGoalDetails(
            @PathVariable UUID userId,
            @PathVariable int goalId) {
    	System.out.println(userId);
    	System.out.println(goalId);
    	
    	Goal result = userGoalService.getGoalDetails(userId, goalId);
        System.out.println(result);
        if (result==null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(result);
    }
    
    
    
    @PutMapping("/updateOneGoalDetails/{userId}/{goalId}")
    public ResponseEntity<UserGoal> updateOneGoalDetails(
            @PathVariable UUID userId,
            @PathVariable int goalId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date duration,
            @RequestParam Long financialGoalValue) {
    	System.out.println(userId);
    	System.out.println(goalId);
    	System.out.println(duration);
    	System.out.println(financialGoalValue);
    	
        UserGoal result = userGoalService.updateGoalDetails(userId, goalId, duration, financialGoalValue);
        System.out.println(result);
        if (result==null) {
        	System.out.println("Done22");
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getNumberOfGoalsForUser/{userId}")
    public int getGoalCountByUserId(@PathVariable("userId") UUID userId) {
        return userGoalService.getGoalCountByUserId(userId);
    }
    
    
    
    @PostMapping("/add-transaction/{userId}/{goalId}")
    public ResponseEntity<Goal> addTransactionToGoal(
            @PathVariable UUID userId,
            @PathVariable int goalId,
            @RequestBody Long transactionAmount) {
		Goal goal = userGoalService.addTransactionToGoal(userId, goalId, transactionAmount);
   		
        return ResponseEntity.ok(goal);
    }
    
 
    
}
