package com.cg.teamoptimus.WealthManagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.teamoptimus.WealthManagement.model.User;
@Repository
public interface IUserRepository extends MongoRepository<User,Integer> {
	
	User findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByuserId(int userId);
	
	
	
	
	

}
