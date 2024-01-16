package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.UserFund;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IUserFundService {
    UserFund addUserFund(UserFund userFund);
    List<UserFund> getAllUserFunds();
    List<UserFund> getUserFundsByUserId(UUID userId);
    List<UserFund> getUserFundByUserIdAndFundId(UUID userId, int fundId);
    UserFund getLatestUserFundByUserIdAndFundId(UUID userId, int fundId);
//    UserFund getUserFundByFundId(String fundId);
    public Double getTotalBalance(UUID userId);
    List<UserFund> filterUserFunds(UUID userId, LocalDate fromDate, LocalDate toDate);
}
