package com.cybage.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LikeDto {
	private Integer complaintId;
	private String complaintDescription;
	private Integer likeCount;
	private List<String> user;
	private List<LocalDateTime> timeStamp;

	public LikeDto() {
		this.user = new ArrayList<>();
		this.timeStamp = new ArrayList<>();
	}

	public LikeDto(Integer complaintId, String complaintDescription, Integer likeCount) {
		this();
		this.complaintId = complaintId;
		this.complaintDescription = complaintDescription;
		this.likeCount = likeCount;
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

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
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
		return "LikeDto [complaintId=" + complaintId + ", complaintDescription=" + complaintDescription + ", likeCount="
				+ likeCount + ", user=" + user + ", timeStamp=" + timeStamp + "]";
	}

}
