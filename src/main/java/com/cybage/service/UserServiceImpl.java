package com.cybage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.ComplaintRepository;
import com.cybage.dao.RatingRepository;
import com.cybage.dao.UserRepository;
import com.cybage.exception.ServerErrorException;
import com.cybage.model.Comments;
import com.cybage.model.Complaint;
import com.cybage.model.Likes;
import com.cybage.model.Ratings;
import com.cybage.model.User;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ComplaintRepository complaintRepository;
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public User userAuthenticated(String userName, String password) {
		return userRepository.findUserByUserNameAndPassword(userName, password);

	}

	@Override
	public User addUser(User user) {
		try {
			return userRepository.save(user);
		} catch (Exception exception) {
			throw new ServerErrorException("Failed to add");
		}

	}

	@Override
	public User getUserByUserId(int userId) {
		return userRepository.findUserByUserId(userId);
	}

	@Override
	public List<Likes> getLikeByComplaintId(Integer complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId).get();
		return complaint.getLikeList();
	}

	@Override
	public List<Comments> getCommentByComplaintId(Integer complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId).get();
		return complaint.getCommentList();
	}


	@Override
	public List<Ratings> getRatingByComplaintId(Integer complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId).get();
		List<Ratings> listRatings = ratingRepository.findAllByComplaintId(complaint);
		return listRatings;
	}

	@Override
	public Complaint getComplaintById(Integer complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId).get();
		return complaintRepository.findById(complaintId).get();
	}

	@Override
	public boolean changePassword(String newPassword, Integer userId) {
		User user = userRepository.getById(userId);
		user.setPassword(newPassword);
		return (userRepository.save(user) != null) ? true : false;

	}

	@Override
	public User getUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

}
