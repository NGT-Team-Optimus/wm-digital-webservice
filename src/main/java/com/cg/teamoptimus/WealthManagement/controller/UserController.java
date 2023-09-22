package com.cg.teamoptimus.WealthManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.teamoptimus.WealthManagement.entity.User;
import com.cg.teamoptimus.WealthManagement.service.IUserService;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	
	@PostMapping("/signup")
	public String register(@RequestBody User user) {
		userService.register(user);
		return "Registered successfully";
	}
		
	@PostMapping("/signin")
	public String signin(@RequestBody String email, String password) {
		userService.signin(email,password);
		return "Logged In";
	}
	
	@PostMapping("/update")
	public String updateUserDetails(@RequestBody User user) {
		userService.updateUserDetails(user);
		return "updated successfully";
	}
	
	


}
