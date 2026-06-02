package com.cybage.dto;

public class AuthenticateDto {
private String userName;
	
	private String password;
	
	public AuthenticateDto() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticateDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthenticateDTO [userName=" + userName + ", password=" + password + "]";
	} 
}
