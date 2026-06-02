package com.cybage.dto;

import java.sql.Timestamp;

public class CitizenDto {
	

	private String name;
	private String userEmail;
	private String username;
	private String userMobileNumber;

	private String password;
	private String userAddress;
	private Timestamp userTimestamp;

	public CitizenDto() {
		// TODO Auto-generated constructor stub
	}
	public CitizenDto(String name, String userEmail, String username, String userMobileNumber, String password,
			String userAddress, Timestamp userTimestamp) {
		super();
		this.name = name;
		this.userEmail = userEmail;
		this.username = username;
		this.userMobileNumber = userMobileNumber;
		this.password = password;
		this.userAddress = userAddress;
		this.userTimestamp = userTimestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Timestamp getUserTimestamp() {
		return userTimestamp;
	}

	public void setUserTimestamp(Timestamp userTimestamp) {
		this.userTimestamp = userTimestamp;
	}

	@Override
	public String toString() {
		return "CitizenDto [name=" + name + ", userEmail=" + userEmail + ", userName=" + username
				+ ", userMobileNumber=" + userMobileNumber + ", password=" + password + ", userAddress=" + userAddress
				+ ", userTimestamp=" + userTimestamp + "]";
	}
}

