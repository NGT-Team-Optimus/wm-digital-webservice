package com.cg.teamoptimus.WealthManagement.repository;

import com.cg.teamoptimus.WealthManagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends MongoRepository<User, UUID> {
	
	User findByEmail(String email);
	Boolean existsByEmail(String email);

	Boolean existsByUserId(UUID userId);

	
	
	
	
	
	
	

}
