package com.cg.teamoptimus.WealthManagement.controller;

import com.cg.teamoptimus.WealthManagement.model.UserFund;
import com.cg.teamoptimus.WealthManagement.services.IUserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user-funds")
public class UserFundController {

    private final IUserFundService userFundService;

    @Autowired
    public UserFundController(IUserFundService userFundService) {
        this.userFundService = userFundService;
    }

    
    
    // Get all user funds.
    @GetMapping
    public ResponseEntity<List<UserFund>> getAllUserFunds() {
        List<UserFund> userFunds = userFundService.getAllUserFunds();
        return new ResponseEntity<>(userFunds, HttpStatus.OK);
    }

    // Create a new user fund.
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserFund> addUserFund(@RequestBody UserFund userFund) {
        UserFund newUserFund = userFundService.addUserFund(userFund);
        return new ResponseEntity<>(newUserFund, HttpStatus.CREATED);
    }
    
    // Get user funds by user ID.
    @GetMapping("/{userId}")
    public ResponseEntity<List<UserFund>> getUserFundsByUserId(@PathVariable UUID userId) {
        List<UserFund> userFunds = userFundService.getUserFundsByUserId(userId);
        return new ResponseEntity<>(userFunds, HttpStatus.OK);
    }
    
 // Get user fund by user ID and fund ID.
    @GetMapping("/{userId}/{fundId}")
    public ResponseEntity<List<UserFund>> getUserFundByUserIdAndFundId(
            @PathVariable UUID userId, @PathVariable int fundId) {
        List<UserFund> userFunds = userFundService.getUserFundByUserIdAndFundId(userId, fundId);
        
        if (!userFunds.isEmpty()) {
            return new ResponseEntity<>(userFunds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getTotalBalance/{userId}")
    public Double getTotalBalance(@PathVariable UUID userId){
        return userFundService.getTotalBalance(userId);
}
    @GetMapping("/filteruserfunds/{userId}")
    public List<UserFund> filterUserFunds(@PathVariable UUID userId,
                                          @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fromDate,
                                          @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate toDate){
        return userFundService.filterUserFunds(userId,fromDate,toDate);

    }
}
