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
    public List<Fund> getAllAvailableFunds(){
        return fundRepo.findAll();

    }
}
