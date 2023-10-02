package com.cg.teamoptimus.WealthManagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.teamoptimus.WealthManagement.model.Goal;
@Repository
public interface IGoalRepository extends MongoRepository<Goal,Integer> {

    @Query(value = "{'userId': ?0}")
	List<Goal> queryGoalByUserId(@Param("id") Long id);
	
	

}
