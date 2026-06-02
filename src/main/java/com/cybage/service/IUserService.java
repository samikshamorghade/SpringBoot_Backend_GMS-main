package com.cybage.service;

import java.util.List;
import java.util.Optional;

import com.cybage.model.Comments;
import com.cybage.model.Complaint;
import com.cybage.model.Likes;
import com.cybage.model.Ratings;
import com.cybage.model.User;

public interface IUserService {

	User userAuthenticated(String userName, String password);

	User addUser(User user);

	User getUserByUserId(int userId);

	List<Likes> getLikeByComplaintId(Integer complaintId);

	List<Comments> getCommentByComplaintId(Integer complaintId);

	List<Ratings> getRatingByComplaintId(Integer complaintId);

	Complaint getComplaintById(Integer complaintId);

	boolean changePassword(String newPassword, Integer userId);

	User getUserByUserName(String userName);
}
