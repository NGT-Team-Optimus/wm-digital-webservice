package com.cg.teamoptimus.WealthManagement.model;

import java.util.UUID;

public class JwtResponse {
	

		UUID userId;
		String token;
		
		
		public JwtResponse( String token,UUID userId) {
			super();
			this.userId = userId;
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public UUID getUserId() {
			return userId;
		}

		public void setUserId(UUID userId) {
			this.userId = userId;
		}
		
		

}


