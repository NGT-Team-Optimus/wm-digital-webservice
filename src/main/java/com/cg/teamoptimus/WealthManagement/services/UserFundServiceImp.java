package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.UserFund;
import com.cg.teamoptimus.WealthManagement.repository.IUserFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserFundServiceImp implements IUserFundService {

    private final IUserFundRepository userFundRepository;
    @Autowired
    private IFundService fundService;

    @Autowired
    public UserFundServiceImp(IUserFundRepository userFundRepository) {
        this.userFundRepository = userFundRepository;
    }

    @Override
    public UserFund addUserFund(UserFund userFund) {
        if (userFund.getTransactionDate() == null) {
            userFund.setTransactionDate(new Date());
        }
        int fundId= fundService.getfundIdByFundName(userFund.getFundName());
        UserFund existingUserFund = getLatestUserFundByUserIdAndFundId(userFund.getUserId(), fundId);

        if (existingUserFund != null) {
            userFund.setOpeningBalance(existingUserFund.getClosingBalance());
        } else {
            // User fund doesn't exist, set the opening balance to 0
            userFund.setOpeningBalance(0);
        }
        
        userFund.setAmount(userFund.getAmount());
        userFund.setFundId(fundId);
        
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
        double totalBalance = 0.0;
        Map<Integer, UserFund> latestFundsMap = new HashMap<>();
        List<UserFund> userFunds = userFundRepository.findByUserId(userId);
        for (UserFund userFund : userFunds) {
            int fundId = userFund.getFundId();
            if (!latestFundsMap.containsKey(fundId)) {
                // If fundId is not present in the map, add it
                latestFundsMap.put(fundId, userFund);
            } else {
                UserFund currentLatest = latestFundsMap.get(fundId);
                if (userFund.getTransactionDate().after(currentLatest.getTransactionDate())) {
                    latestFundsMap.put(fundId, userFund);
                }
            }
        }
        // Calculate total balance using the latest UserFunds
        for (UserFund latestUserFund : latestFundsMap.values()) {
            totalBalance += latestUserFund.getClosingBalance();
        }
        return totalBalance;
    }
    @Override
    public List<UserFund> filterUserFunds(UUID userId,LocalDate fromDate,LocalDate toDate){
        List<UserFund> userFunds=getUserFundsByUserId(userId);
        List<UserFund> filteredUserFunds = userFunds.stream()
                .filter(userFund -> {
                    LocalDate transactionDate = userFund.getTransactionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return !transactionDate.isBefore(fromDate) && !transactionDate.isAfter(toDate);
                })
                .collect(Collectors.toList());
        return filteredUserFunds;

    }

}
