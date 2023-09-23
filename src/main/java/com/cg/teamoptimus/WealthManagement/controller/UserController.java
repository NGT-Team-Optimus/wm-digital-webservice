package com.cg.teamoptimus.WealthManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	


}
