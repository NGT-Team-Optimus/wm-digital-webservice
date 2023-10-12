package com.cg.teamoptimus.WealthManagement.services;

import java.util.ArrayList;

import com.cg.teamoptimus.WealthManagement.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	IUserRepository userRepo;
	Logger logger = LoggerFactory.getLogger(IUserService.class);

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.cg.teamoptimus.WealthManagement.model.User user=userRepo.findByEmail(email);
		//logger.info("email"+email, "userEmail"+user.getEmail());
		if(email.equals(user.getEmail())) {
			return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not Found!!");
		}
	}




}







