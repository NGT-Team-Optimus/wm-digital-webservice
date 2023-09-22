package com.cg.teamoptimus.WealthManagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.teamoptimus.WealthManagement.entity.Role;

public interface IRoleRepository extends MongoRepository<Role,Integer> {
	Optional<Role> findByName(String name);

}
