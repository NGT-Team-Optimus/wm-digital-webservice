package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.UserFund;
import com.cg.teamoptimus.WealthManagement.repository.IUserFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
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
        if (userFund.getTransactionDate() == null) {
            userFund.setTransactionDate(new Date());
        }
        
        UserFund existingUserFund = getLatestUserFundByUserIdAndFundId(userFund.getUserId(), userFund.getFundId());

        if (existingUserFund != null) {
            userFund.setOpeningBalance(existingUserFund.getClosingBalance());
        } else {
            // User fund doesn't exist, set the opening balance to 0
            userFund.setOpeningBalance(0);
        }
        
        userFund.setAmount(userFund.getAmount());
        
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
    public UserFund getLatestUserFundByUserIdAndFundId(UUID userId, int fundId) {
        Sort sortByTransactionDateDesc = Sort.by(Sort.Direction.DESC, "transactionDate");
        Pageable pageable = PageRequest.of(0, 1, sortByTransactionDateDesc);
        Page<UserFund> matchingUserFunds = userFundRepository.findByUserIdAndFundId(userId, fundId, pageable);

        return matchingUserFunds.isEmpty() ? null : matchingUserFunds.getContent().get(0);
    }

    @Override
    public List<UserFund> getUserFundByUserIdAndFundId(UUID userId, int fundId) {
        int pageSize = 10; // Set the page size to retrieve 10 records
        Sort sortByTransactionDateDesc = Sort.by(Sort.Direction.DESC, "transactionDate");
        Pageable pageable = PageRequest.of(0, pageSize, sortByTransactionDateDesc);
        Page<UserFund> matchingUserFunds = userFundRepository.findByUserIdAndFundId(userId, fundId, pageable);
        
        List<UserFund> userFunds = matchingUserFunds.getContent();
        return userFunds;
    }
    @Override
    public Double getTotalBalance(UUID userId) {
        double totalBalance = 0L;
        List<UserFund> userFunds = userFundRepository.findByUserId(userId);
        for (UserFund userFund : userFunds) {
            totalBalance += userFund.getClosingBalance();

        }
        return totalBalance;
    }

}
