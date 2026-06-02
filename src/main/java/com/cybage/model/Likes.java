package com.cybage.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "like_table")
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_id")
	private Integer likeId;
	
	@Column(name = "like_timestamp")
	private LocalDateTime likeTimestamp;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private User userId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "complaintId")
	@JsonIgnore
	private Complaint complaintId;
	
	public Likes() {
		
	}

	public Likes(Integer likeId, LocalDateTime likeTimestamp, User userId, Complaint complaintId) {
		super();
		this.likeId = likeId;
		this.likeTimestamp = likeTimestamp;
		this.userId = userId;
		this.complaintId = complaintId;
	}

	public Likes(User users, Complaint complaint, LocalDateTime now) {
		
		this.userId = users;
		this.complaintId = complaint;
		this.likeTimestamp=now;
	}

	public Integer getLikeId() {
		return likeId;
	}

	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}

	public LocalDateTime getLikeTimestamp() {
		return likeTimestamp;
	}

	public void setLikeTimestamp(LocalDateTime likeTimestamp) {
		this.likeTimestamp = likeTimestamp;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Complaint getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Complaint complaintId) {
		this.complaintId = complaintId;
	}
	
	
	@Override
	public String toString() {
		return "Likes [likeId=" + likeId + ", likeTimestamp=" + likeTimestamp + ", userId=" + userId + ", complaintId="
				+ complaintId + "]";
	}
	
	

}
