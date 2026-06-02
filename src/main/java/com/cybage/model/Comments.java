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
@Table(name = "comment_table")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer commentId;
	
	@Column(name = "comment_timestamp")
	private LocalDateTime commentTimestamp;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User userId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "complaintId")
	@JsonIgnore
	private Complaint complaintId;
	
	@Column(name = "comment")
	private String comment;
	
	public Comments() {
		// TODO Auto-generated constructor stub
	}

	public Comments(Integer commentId, LocalDateTime commentTimestamp, User userId, Complaint complaintId, String comment) {
		super();
		this.commentId = commentId;
		this.commentTimestamp = commentTimestamp;
		this.userId = userId;
		this.complaintId = complaintId;
		this.comment = comment;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public LocalDateTime getCommentTimestamp() {
		return commentTimestamp;
	}

	public void setCommentTimestamp(LocalDateTime commentTimestamp) {
		this.commentTimestamp = commentTimestamp;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
//
//	@Override
//	public String toString() {
//		return "Comments [commentId=" + commentId + ", commentTimestamp=" + commentTimestamp + ", userId=" + userId
//				+ ", complaintId=" + complaintId + ", comment=" + comment + "]";
//	}
	
	

}
