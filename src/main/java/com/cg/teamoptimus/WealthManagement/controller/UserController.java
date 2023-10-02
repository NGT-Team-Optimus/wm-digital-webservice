package com.cg.teamoptimus.WealthManagement.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.core.Authentication;

import com.cg.teamoptimus.WealthManagement.helper.JwtUtil;
import com.cg.teamoptimus.WealthManagement.model.LoginResponse;
import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.services.CustomUserDetailsService;
import com.cg.teamoptimus.WealthManagement.services.IUserService;

import jakarta.validation.Valid;




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
	public String register(@RequestBody User user) {
		userService.register(user);
		return "Registered successfully";
	}
		
	
	@PostMapping("/update")
	public String updateUserDetails(@RequestBody User user) {
		userService.updateUserDetails(user);
		return "updated successfully";
	}
	@GetMapping("/all")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
		
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		userService.loginUser(user);
		return "Logged In";
	}
	
	
	 @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) {
		// authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(User.getUsername(), User.getPassword()));
		 org.springframework.security.core.Authentication authentication =authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());

	        final String jwt = jwtUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new LoginResponse(jwt));
	    }

}
