package com.cg.teamoptimus.WealthManagement.repository;

import com.cg.teamoptimus.WealthManagement.model.UserFund;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserFundRepository extends MongoRepository<UserFund, String> {
    List<UserFund> findByUserId(UUID userId);
    UserFund findByFundId(String fundId);
    void deleteByFundId(String fundId);
}
