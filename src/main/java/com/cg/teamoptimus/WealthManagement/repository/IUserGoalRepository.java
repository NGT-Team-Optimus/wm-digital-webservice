package com.cg.teamoptimus.WealthManagement.repository;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.UserGoal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserGoalRepository extends MongoRepository<UserGoal,UUID> {

	
	Goal findGoalByUserUserIdAndGoalsGoalId(UUID userId, int goalId);
	
	UserGoal findByUserUserId(UUID userID);

    boolean existsByUserUserId(UUID userId);
}
