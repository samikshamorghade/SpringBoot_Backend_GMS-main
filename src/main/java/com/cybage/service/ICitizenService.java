package com.cybage.service;

import java.util.List;

import com.cybage.model.Comments;
import com.cybage.model.Complaint;
import com.cybage.model.Likes;
import com.cybage.model.Ratings;

public interface ICitizenService {
	void makeComplaint(Complaint complaint);

	List<Complaint> viewAllComplaintsById(Integer id);
	
	List<Complaint> viewAllComplaints();
	

	
	void enableComplaintReminder(Integer id);
	
	void dissableComplaintReminder(Integer id);
	
	String getStatusById(Integer id);
	
	void reopenComplaint(Integer id);
	
	void likeComplaint(Integer id);
	
	//boolean changeStatusReopen(String oldStatus,String newStatus,int id);

	boolean changeStatusReopen( Integer id);
	
	int likeComplaintById(Integer id);
	
	List<Comments> viewAllComments();
	
	void addComment(Comments comments);
	public void addAndRemoveLikes(int complaintId, int userId);
	String addRatingToComplaint(int complaintId, int userId, int rating);
	List<Ratings> viewAllRatings();
	List<Ratings> viewAllRatingsByCompliantId(int complaintId);
	List<Likes> viewAllLikes();
	List<Likes> viewAllLikesByComplaintId(int complaintId);
	int getAllLikes(Integer complaintNo);
	public boolean LikedOrNot(int complaintId,int userId);
}
