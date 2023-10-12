package com.cg.teamoptimus.WealthManagement.controller;


import com.cg.teamoptimus.WealthManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
	public User register(@RequestBody User user) {
		User result=userService.register(user);
		return result;
	}
	
	@PostMapping("/update")
	public String updateUserDetails(@RequestBody User user) {
		userService.updateUserDetails(user);
		return "updated successfully";
	}
/*	@PostMapping("/login")
	public String loginUser(@RequestBody JwtRequest jwtRequest) {
		userService.loginUser(jwtRequest);
		return "Logged In";
	}

 */



}
