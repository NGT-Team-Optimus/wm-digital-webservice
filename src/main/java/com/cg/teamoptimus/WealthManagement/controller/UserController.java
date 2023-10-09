package com.cg.teamoptimus.WealthManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.Authentication;

import com.cg.teamoptimus.WealthManagement.helper.JwtUtil;
import com.cg.teamoptimus.WealthManagement.model.JwtResponse;
import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
import com.cg.teamoptimus.WealthManagement.services.CustomUserDetailsService;
import com.cg.teamoptimus.WealthManagement.services.IUserService;






@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	 @Autowired
	    private AuthenticationManager authenticationManager;
	
	@PostMapping("/signup")
	public String register(@RequestBody JwtRequest user) {
		userService.register(user);
		return "Registered successfully";
	}
	
	@PutMapping("/update")
	public String updateUserDetails(@RequestBody JwtRequest user) {
		userService.updateUserDetails(user);
		return "updated successfully";
	}
	@PostMapping("/login")
	public String loginUser(@RequestBody JwtRequest user) {
		userService.loginUser(user);
		return "Logged In";
	}
	

}
