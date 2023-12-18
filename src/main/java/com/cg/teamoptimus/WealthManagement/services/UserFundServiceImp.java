package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.UserFund;
import com.cg.teamoptimus.WealthManagement.repository.IUserFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserFundServiceImp implements IUserFundService {

    private final IUserFundRepository userFundRepository;

    @Autowired
    public UserFundServiceImp(IUserFundRepository userFundRepository) {
        this.userFundRepository = userFundRepository;
    }

    @Override
    public UserFund addUserFund(UserFund userFund) {
        return userFundRepository.save(userFund);
    }

    @Override
    public List<UserFund> getAllUserFunds() {
        return userFundRepository.findAll();
    }

    @Override
    public List<UserFund> getUserFundsByUserId(UUID userId) {
        return userFundRepository.findByUserId(userId);
    }

    @Override
    public UserFund getUserFundByFundId(String fundId) {
        return userFundRepository.findByFundId(fundId);
    }

    @Override
    public UserFund updateUserFund(String fundId, UserFund userFund) {
        UserFund existingFund = userFundRepository.findByFundId(fundId);
        if (existingFund != null) {
            // Update the necessary fields in the existing fund and save
            existingFund.setUserId(userFund.getUserId());
            existingFund.setOpeningBalance(userFund.getOpeningBalance());
            existingFund.setAmount(userFund.getAmount());
            existingFund.setTransactionDate(userFund.getTransactionDate());
            existingFund.setTransactionType(userFund.getTransactionType());
            existingFund.setClosingBalance(userFund.getClosingBalance());
            return userFundRepository.save(existingFund);
        }
        return null; // Return null if the fund doesn't exist
    }

    @Override
    public void deleteUserFund(String fundId) {
        userFundRepository.deleteByFundId(fundId);
    }
}
