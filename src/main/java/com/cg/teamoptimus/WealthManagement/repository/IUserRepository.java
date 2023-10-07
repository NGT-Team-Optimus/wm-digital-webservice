package com.cg.teamoptimus.WealthManagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.teamoptimus.WealthManagement.model.Goal;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;

@Repository
public interface IUserRepository extends MongoRepository<JwtRequest,Integer> {
	
	JwtRequest findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByuserId(String userId);

	
	
	
	
	
	
	

}
