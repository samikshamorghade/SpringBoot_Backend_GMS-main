package com.cybage.model;

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
@Table(name = "rating_table")
public class Ratings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rating_id")
	private Integer ratingId;
	
	@Column(name = "rating_timestamp")
	private LocalDateTime ratingTimestamp;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private User userId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "complaintId")
	@JsonIgnore
	private Complaint complaintId;
	
	@Column(name = "rating")
	private int rating;
	
	public Ratings() {
	}

	public Ratings(Integer ratingId, LocalDateTime ratingTimestamp, User userId, Complaint complaintId, int rating) {
		super();
		this.ratingId = ratingId;
		this.ratingTimestamp = ratingTimestamp;
		this.userId = userId;
		this.complaintId = complaintId;
		this.rating = rating;
	}

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public LocalDateTime getRatingTimestamp() {
		return ratingTimestamp;
	}

	public void setRatingTimestamp(LocalDateTime ratingTimestamp) {
		this.ratingTimestamp = ratingTimestamp;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

//	@Override
//	public String toString() {
//		return "Ratings [ratingId=" + ratingId + ", ratingTimestamp=" + ratingTimestamp + ", userId=" + userId
//				+ ", complaintId=" + complaintId + ", rating=" + rating + "]";
//	}
//	
	

}
