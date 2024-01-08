package com.cg.teamoptimus.WealthManagement.repository;

import com.cg.teamoptimus.WealthManagement.model.Fund;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFundRepository extends MongoRepository<Fund,Integer> {
    Fund findByFundName(String fundName);
}
