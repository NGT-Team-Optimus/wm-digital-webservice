package com.cg.teamoptimus.WealthManagement.controller;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.services.IGoalService;

@RestController
@RequestMapping("/goals")
public class GoalController {
	@Autowired
	IGoalService goalService;
	
	@GetMapping("/get")
	public List<Goal> getAllGoals(){
		return goalService.getAllGoals();
	}


}
