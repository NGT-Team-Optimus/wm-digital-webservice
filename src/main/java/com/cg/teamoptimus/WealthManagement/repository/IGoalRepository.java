package com.cg.teamoptimus.WealthManagement.repository;

import com.cg.teamoptimus.WealthManagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.teamoptimus.WealthManagement.model.Goal;

import java.util.List;
import java.util.UUID;

@Repository
public interface IGoalRepository extends MongoRepository<Goal,Integer> {

    Goal findByGoalId(int goalId);
}
