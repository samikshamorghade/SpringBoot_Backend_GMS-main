package com.cybage.dto;

public class HodDto {
	private Integer hodId;
	

	private String hodName; 
    private String email;
    private String hodMobileNumber;
    private String hodUserName; 
    private String password;
   private String hodAddress;
//   hodName : new FormControl(''),
//   email : new FormControl('', [Validators.required, Validators.email]),
//   hodMobileNumber: new FormControl(''),
//   hodUserName :new FormControl(''),
//   password: new FormControl(''),
//   confirmPassword: new FormControl(''),
//   hodAddress: new FormControl('')
   HodDto(){
	   
   }

public HodDto(String hodName, String email, String hodMobileNumber, String hodUserName, String password,
		String hodAddress) {
	super();
	this.hodName = hodName;
	this.email = email;
	this.hodMobileNumber = hodMobileNumber;
	this.hodUserName = hodUserName;
	this.password = password;
	this.hodAddress = hodAddress;
}
public Integer getHodId() {
	return hodId;
}

public void setHodId(Integer hodId) {
	this.hodId = hodId;
}

public String getHodName() {
	return hodName;
}

public void setHodName(String hodName) {
	this.hodName = hodName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getHodMobileNumber() {
	return hodMobileNumber;
}

public void setHodMobileNumber(String hodMobileNumber) {
	this.hodMobileNumber = hodMobileNumber;
}

public String getHodUserName() {
	return hodUserName;
}

public void setHodUserName(String hodUserName) {
	this.hodUserName = hodUserName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getHodAddress() {
	return hodAddress;
}

public void setHodAddress(String hodAddress) {
	this.hodAddress = hodAddress;
}

@Override
public String toString() {
	return "HodDto [hodName=" + hodName + ", email=" + email + ", hodMobileNumber=" + hodMobileNumber + ", hodUserName="
			+ hodUserName + ", password=" + password + ", hodAddress=" + hodAddress + "]";
}
   
}
