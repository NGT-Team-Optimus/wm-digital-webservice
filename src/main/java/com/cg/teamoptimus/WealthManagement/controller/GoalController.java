package com.cg.teamoptimus.WealthManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
