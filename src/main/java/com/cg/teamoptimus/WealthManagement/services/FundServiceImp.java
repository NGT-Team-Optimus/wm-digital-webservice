package com.cg.teamoptimus.WealthManagement.services;

import com.cg.teamoptimus.WealthManagement.model.Fund;
import com.cg.teamoptimus.WealthManagement.repository.IFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundServiceImp implements IFundService{
    @Autowired
    IFundRepository fundRepo;
    @Override
    public List<Fund> getAllAvailableFunds(){
        return fundRepo.findAll();

    }
    @Override
    public int getfundIdByFundName(String fundName) {
        Fund fund=fundRepo.findByFundName(fundName);
        return fund.getFundId();
    }
}
