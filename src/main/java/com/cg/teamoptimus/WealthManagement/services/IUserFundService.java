package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.UserFund;

import java.util.List;
import java.util.UUID;

public interface IUserFundService {
    UserFund addUserFund(UserFund userFund);
    List<UserFund> getAllUserFunds();
    List<UserFund> getUserFundsByUserId(UUID userId);
    UserFund getUserFundByFundId(String fundId);
    UserFund updateUserFund(String fundId, UserFund userFund); // Modified method signature
    void deleteUserFund(String fundId);
}
