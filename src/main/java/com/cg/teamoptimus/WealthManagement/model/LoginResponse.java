package com.cg.teamoptimus.WealthManagement.model;

public class LoginResponse {
	


		String token;
		
		public LoginResponse(){	
		}

		public LoginResponse(String token) {
			super();
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
		
		
		
	}



