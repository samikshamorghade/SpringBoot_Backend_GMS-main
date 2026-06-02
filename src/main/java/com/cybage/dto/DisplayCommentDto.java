package com.cybage.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DisplayCommentDto {
	
	private Integer complaintId;	
	private String comment;
	private String user;
	private LocalDateTime commentTimestamp;
	
	public DisplayCommentDto(){}
	
	public DisplayCommentDto(Integer complaintId, String comment, String user, LocalDateTime commentTimestamp) {
		super();
		this.complaintId = complaintId;
		this.comment = comment;
		this.user = user;
		this.commentTimestamp = commentTimestamp;
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDateTime getCommentTimestamp() {
		return commentTimestamp;
	}

	public void setCommentTimestamp(LocalDateTime commentTimestamp) {
		this.commentTimestamp = commentTimestamp;
	}

	@Override
	public String toString() {
		return "DisplayCommentDto [complaintId=" + complaintId + ", comment=" + comment + ", user=" + user
				+ ", commentTimestamp=" + commentTimestamp + "]";
	}
	
	
}
