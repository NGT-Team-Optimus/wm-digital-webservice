package com.cg.teamoptimus.WealthManagement.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cg.teamoptimus.WealthManagement.helper.JwtUtil;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import com.cg.teamoptimus.WealthManagement.model.JwtResponse;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.services.CustomUserDetailsService;
import com.cg.teamoptimus.WealthManagement.services.UserServiceImp;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserServiceImp userService;
    
    @RequestMapping(value = "/user/signin", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	
        try {
            // Authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            // Username not found
            return ResponseEntity.status(401).body("Unauthorized: User not found");
        } catch (BadCredentialsException e) {
            // Wrong password
            return ResponseEntity.status(401).body("Unauthorized: Wrong username or password");
        }

        // If authentication is successful, generate and return a JWT
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        UUID userId = getUserIDFromEmail(jwtRequest.getEmail());
        
        //  return ResponseEntity.ok(new JwtResponse(token));
        
        JwtResponse jwtResponse = new JwtResponse(token, userId);
        return ResponseEntity.ok(jwtResponse);
        
    }
    private UUID getUserIDFromEmail(String email) {
        User user = userService.getUserByEmail(email); // Replace with the actual method in your service or repository
        if (user != null) {
            return user.getUserId();
        } else {
            return null; // Handle the case where the user is not found
        }
    }

}

