package com.cg.teamoptimus.WealthManagement.controller;

import com.cg.teamoptimus.WealthManagement.model.UserFund;
import com.cg.teamoptimus.WealthManagement.services.IUserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    // Update user fund by fund ID
    @PutMapping("/{fundId}")
    public ResponseEntity<UserFund> updateUserFund(@PathVariable String fundId, @RequestBody UserFund userFund) {
        UserFund updatedUserFund = userFundService.updateUserFund(fundId, userFund);
        return new ResponseEntity<>(updatedUserFund, HttpStatus.OK);
    }

    // Delete user fund by fund ID
    @DeleteMapping("/{fundId}")
    public ResponseEntity<?> deleteUserFund(@PathVariable String fundId) {
        userFundService.deleteUserFund(fundId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
@GetMapping("/getTotalBalance/{userId}")
    public Double getTotalBalance(@PathVariable UUID userId){
        return userFundService.getTotalBalance(userId);
}
}
