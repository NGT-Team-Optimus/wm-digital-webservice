package com.cg.teamoptimus.WealthManagement.controller;

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
import com.cg.teamoptimus.WealthManagement.model.LoginResponse;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.services.CustomUserDetailsService;


@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody User User) throws Exception {
        try {
            // Authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(User.getUsername(), User.getPassword()));
        } catch (UsernameNotFoundException e) {
            // Username not found
            return ResponseEntity.status(401).body("Unauthorized: Username not found");
        } catch (BadCredentialsException e) {
            // Wrong password
            return ResponseEntity.status(401).body("Unauthorized: Wrong username or password");
        }

        // If authentication is successful, generate and return a JWT
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(User.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(token));
    }
    
   
}