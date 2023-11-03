package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;
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
    IGoalService goalService;
    @Autowired
    IUserService userService;
    @Override
    public UserGoal addGoalsByUser(@NotNull UserGoal userGoal) {
        User user = userService.getUserByUserId(userGoal.getUser().getUserId());
        userGoal.setUser(user);
        for (Goal goal : userGoal.getGoals()) {
            Goal goal1 = goalService.getGoalByGoalId(goal.getGoalId());
            goal.setGoalName(goal1.getGoalName());
        }
        return userGoalRepo.save(userGoal);
    }


}
