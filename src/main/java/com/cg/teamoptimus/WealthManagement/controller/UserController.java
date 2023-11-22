
package com.cg.teamoptimus.WealthManagement.controller;

import com.cg.teamoptimus.WealthManagement.model.User;
import com.cg.teamoptimus.WealthManagement.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;
	 @Autowired
	    private AuthenticationManager authenticationManager;
	
@PostMapping("/signup")
public UUID register(@RequestBody User user) {
    return userService.register(user);
}

	@GetMapping("/api/forget_password/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable("email") String email){
		String token=userService.forgotPassword(email);
		return ResponseEntity.ok(token);

	}
	@PostMapping("/api/confirm_password")
	public ResponseEntity<String> confirmPassword(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		String code = request.get("code");
		String newPassword = request.get("newPassword");
		String result = userService.confirmPassword(email, code, newPassword);
		return ResponseEntity.ok(result);
	}

    @PutMapping("/update")
    public String updateUserDetails(@RequestBody User user) {
        return ((UserController) userService).updateUserDetails(user);
    }
}

