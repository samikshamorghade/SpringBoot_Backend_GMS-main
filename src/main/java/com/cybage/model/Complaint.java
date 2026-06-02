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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.id.IntegralDataTypeHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "complaint")
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "complaint_id")
	private Integer complaintId;
	@Column(name = "complaint_description")
	private String complaintDescription;
	@Column(name = "complaint_register_date")
	private LocalDate complaintRegisterDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "complaint_resolve_date")
	private LocalDate complaintResolveDate;
	@Column(name = "complaint_reminder")
	private boolean complaintReminder;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "departmentId")
	@JsonIgnoreProperties({"departmentId","departmentTimestamp","hodId"})
	private Department departmentId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="userId")
	
	private User userId;
	
	private Integer likeCount;
	private Integer commentCount;
	private Integer ratingCount;
	
	@OneToMany(mappedBy = "complaintId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Likes>likeList;
	
	@OneToMany(mappedBy = "complaintId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Comments>commentList;
	
	@OneToMany(mappedBy = "ratingId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Ratings>ratingList;
	
	public Complaint() {
	
	}

	

	public boolean isComplaintReminder() {
		return complaintReminder;
	}



	public void setComplaintReminder(boolean complaintReminder) {
		this.complaintReminder = complaintReminder;
	}



	public Integer getLikeCount() {
		return likeCount;
	}



	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}



	public Integer getCommentCount() {
		return commentCount;
	}



	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}



	public Integer getRatingCount() {
		return ratingCount;
	}



	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
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

	public LocalDate getComplaintRegisterDate() {
		return complaintRegisterDate;
	}

	public void setComplaintRegisterDate(LocalDate complaintRegisterDate) {
		this.complaintRegisterDate = complaintRegisterDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getComplaintResolveDate() {
		return complaintResolveDate;
	}

	public void setComplaintResolveDate(LocalDate complaintResolveDate) {
		this.complaintResolveDate = complaintResolveDate;
	}

	public Department getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public List<Likes> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Likes> likeList) {
		this.likeList = likeList;
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
//		return "Complaint [complaintId=" + complaintId + ", complaintDescription=" + complaintDescription
//				+ ", complaintRegisterDate=" + complaintRegisterDate + ", status=" + status + ", complaintResolveDate="
//				+ complaintResolveDate + ", departmentId=" + departmentId + ", userId=" + userId + ", likeList="
//				+ likeList + ", commentList=" + commentList + ", ratingList=" + ratingList + "]";
//	}
//	
	

}
