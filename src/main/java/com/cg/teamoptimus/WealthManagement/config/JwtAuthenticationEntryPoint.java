package com.cg.teamoptimus.WealthManagement.config;

import java.io.IOException;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;



@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


	@Override
	public void commence(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException)
			throws IOException, jakarta.servlet.ServletException {
	
		response.sendError(401, "Unauthorized");
		
	}

}