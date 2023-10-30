package com.cg.teamoptimus.WealthManagement.controller;

import com.cg.teamoptimus.WealthManagement.model.UserGoal;
import com.cg.teamoptimus.WealthManagement.services.IUserGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserGoalController {
    @Autowired
    IUserGoalService userGoalService;
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

}
