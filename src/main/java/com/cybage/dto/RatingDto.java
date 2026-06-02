package com.cybage.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cybage.model.Ratings;

public class RatingDto {
	private Integer complaintId;
	private String complaintDescription;
	private List<Ratings> rating;

	public RatingDto() {

	}

	public RatingDto(Integer complaintId, String complaintDescription, List<Ratings> rating) {
		super();
		this.complaintId = complaintId;
		this.complaintDescription = complaintDescription;
		this.rating = rating;
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

	public List<Ratings> getRating() {
		return rating;
	}

	public void setRating(List<Ratings> rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "RatingDto [complaintId=" + complaintId + ", complaintDescription=" + complaintDescription + ", rating="
				+ rating + "]";
	}

}
