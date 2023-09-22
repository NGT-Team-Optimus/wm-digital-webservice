package com.cg.teamoptimus.WealthManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.teamoptimus.WealthManagement.entity.User;

public interface IUserRepository extends MongoRepository<User,Integer> {
	
	String findPasswordByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByuserId(int userId);

}
