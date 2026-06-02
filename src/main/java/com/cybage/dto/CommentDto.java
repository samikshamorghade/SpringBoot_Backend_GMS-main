package com.cybage.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDto {
	private Integer complaintId;
	private String complaintDescription;
	private Integer commentCount;
	private List <String> comment;
	
	
	private List<String> user;
	private List<LocalDateTime> timeStamp;
	
	public CommentDto() {
		this.user = new ArrayList<>();
		this.timeStamp= new ArrayList<>();
		this.comment= new ArrayList<>();
	
	}
	
	

	public List<String> getComment() {
		return comment;
	}



	public void setComment(List<String> comment) {
		this.comment = comment;
	}



	public CommentDto(Integer complaintId, String complaintDescription, Integer commentCount) {
		this();
		this.complaintId = complaintId;
		this.complaintDescription = complaintDescription;
		this.commentCount = commentCount;
		
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public String getComplaintDescription() {
		return complaintDescription;
	}

	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public List<String> getUser() {
		return user;
	}

	public void setUser(List<String> user) {
		this.user = user;
	}

	public List<LocalDateTime> getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(List<LocalDateTime> timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "CommentDto [complaintId=" + complaintId + ", complaintDescription=" + complaintDescription
				+ ", commentCount=" + commentCount + ", comment=" + comment + ", user=" + user + ", timeStamp="
				+ timeStamp + "]";
	}
	
}
