package com.cg.teamoptimus.WealthManagement.repository;

import com.cg.teamoptimus.WealthManagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User,Integer> {
	
	User findByEmail(String email);
	Boolean existsByEmail(String email);

	Boolean existsByUserId(int userId);

	
	
	
	
	
	
	

}
