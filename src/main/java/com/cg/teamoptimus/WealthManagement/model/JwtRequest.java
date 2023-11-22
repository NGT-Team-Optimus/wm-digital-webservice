package com.cg.teamoptimus.WealthManagement.model;

import java.util.List;



public class JwtRequest {
	
	private String email;

	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

		public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
		public JwtRequest(String email,
				 String password) {
			super();

			this.email = email;
			this.password = password;

		}
		
		@Override
		public String toString() {
			return " email=" + email + ", password=" + password;
		}

}


