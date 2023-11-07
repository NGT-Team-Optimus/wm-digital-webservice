//package com.cg.teamoptimus.WealthManagement.controller;
//
//
//import com.cg.teamoptimus.WealthManagement.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
////import org.springframework.security.core.Authentication;
//
//import com.cg.teamoptimus.WealthManagement.helper.JwtUtil;
//import com.cg.teamoptimus.WealthManagement.model.JwtResponse;
//import com.cg.teamoptimus.WealthManagement.model.JwtRequest;
//import com.cg.teamoptimus.WealthManagement.services.CustomUserDetailsService;
//import com.cg.teamoptimus.WealthManagement.services.IUserService;
//
//import java.util.UUID;
//
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//	@Autowired
//	IUserService userService;
//	@Autowired
//	JwtUtil jwtUtil;
//	@Autowired
//	CustomUserDetailsService customUserDetailsService;
//
//	 @Autowired
//	    private AuthenticationManager authenticationManager;
//	
//	@PostMapping("/signup")
//	public UUID register(@RequestBody User user) {
//		UUID result=userService.register(user);
//		return result;
//	}
//	
//	@PutMapping("/update")
//	public String updateUserDetails(@RequestBody User user) {
//		userService.updateUserDetails(user);
//		return "updated successfully";
//	}
//
//
//}


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
//=======
//
//>>>>>>> 2a386a040779623e6d2c47ec52ae91203176b4f8
}

