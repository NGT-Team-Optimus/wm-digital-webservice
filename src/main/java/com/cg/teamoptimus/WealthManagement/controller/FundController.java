package com.cg.teamoptimus.WealthManagement.controller;

import com.cg.teamoptimus.WealthManagement.model.Fund;
import com.cg.teamoptimus.WealthManagement.services.IFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FundController {
    @Autowired
    IFundService fundService;
    @GetMapping("/getAllAvailableFunds")
    public List<Fund> getAllAvailableFunds(){
        return fundService.getAllAvailableFunds();
    }
}
