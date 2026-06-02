
package com.cybage.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "user_emailId")
	private String emailId;
	
	@Column(name = "user_name",unique = true)
	private String userName;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_mobile_number",length = 10)
	private String userMobileNumber;
	
	@Column(name = "user_address")
	private String userAddress;
	
	@Enumerated(EnumType.STRING)
	private Role userRole;
	
	@Column(name = "user_timestamp")
	private LocalDate userTimestamp;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "user_login_attempt")
	private int loginAttempt;
	
	@OneToMany(mappedBy = "userId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Complaint>complaintList;
	
	@OneToMany(mappedBy = "userId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Likes>likeList;
	
	@OneToMany(mappedBy = "userId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Comments>commentList;
	
	@OneToMany(mappedBy = "userId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Ratings>ratingList;
	
	public User() {	}
	
	public User(String name, String emailId, String userName, String password, String userMobileNumber,
			String userAddress, LocalDate userTimestamp) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
		this.userMobileNumber = userMobileNumber;
		this.userAddress = userAddress;
		this.userTimestamp = userTimestamp;		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public LocalDate getUserTimestamp() {
		return userTimestamp;
	}

	public void setUserTimestamp(LocalDate userTimestamp) {
		this.userTimestamp = userTimestamp;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public List<Complaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<Complaint> complaintList) {
		this.complaintList = complaintList;
	}

	public List<Likes> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Likes> likeList) {
		this.likeList = likeList;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<Comments> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comments> commentList) {
		this.commentList = commentList;
	}

	public List<Ratings> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<Ratings> ratingList) {
		this.ratingList = ratingList;
	}



//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", userName=" + userName
//				+ ", password=" + password + ", userMobileNumber=" + userMobileNumber + ", userAddress=" + userAddress
//				+ ", userRole=" + userRole + ", userTimestamp=" + userTimestamp + ", departmentName=" + departmentName
//				+ ", loginAttempt=" + loginAttempt + ", complaintList=" + complaintList + ", likeList=" + likeList
//				+ ", commentList=" + commentList + ", ratingList=" + ratingList + "]";
//	}

	
	
}
