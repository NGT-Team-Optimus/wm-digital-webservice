package com.cg.teamoptimus.WealthManagement.repository;

import com.cg.teamoptimus.WealthManagement.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends MongoRepository<Transaction, Integer> {
    // Add custom methods if needed for specific queries or operations

}
