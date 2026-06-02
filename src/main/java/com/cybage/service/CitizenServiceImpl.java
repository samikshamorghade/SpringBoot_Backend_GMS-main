package com.cybage.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.CommentRepository;
import com.cybage.dao.ComplaintRepository;
import com.cybage.dao.LikeRepository;
import com.cybage.dao.RatingRepository;
import com.cybage.dao.UserRepository;
import com.cybage.model.Comments;
import com.cybage.model.Complaint;
import com.cybage.model.Likes;
import com.cybage.model.Ratings;
import com.cybage.model.Status;
import com.cybage.model.User;

@Service
public class CitizenServiceImpl implements ICitizenService {

	@Autowired
	ComplaintRepository complaintRepository;

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	LikeRepository likeRepository;
	@Autowired
	RatingRepository ratingRepository;


	@Override
	public void makeComplaint(Complaint complaint) {
		complaintRepository.save(complaint);

	}

	@Override
	public List<Complaint> viewAllComplaintsById(Integer id) {

		return complaintRepository.getComplaintsByUserId(id);
	}

	@Override
	public List<Complaint> viewAllComplaints() {

		// System.out.println(complaintRepository.findAll());
		return complaintRepository.findAll();
	}

	@Override
	public void enableComplaintReminder(Integer id) {
		Complaint complaint = complaintRepository.findById(id).get();
		complaint.setComplaintReminder(true);
		complaintRepository.save(complaint);

	}

	@Override
	public void dissableComplaintReminder(Integer id) {
		complaintRepository.dissableComplaintReminder(id);

	}

	@Override
	public String getStatusById(Integer id) {

		return complaintRepository.getStatusById(id);
	}

	@Override
	public void reopenComplaint(Integer id) {
		
		Complaint complaint = complaintRepository.findById(id).get();
		complaint.setStatus(Status.REOPEN);
		complaintRepository.save(complaint);
		

	}

	@Override
	public void likeComplaint(Integer id) {

	}

	
	@Override
	public boolean changeStatusReopen(Integer id) {
		
		if(complaintRepository.getStatusByComplaintId(id).toString().equals(Status.COMPLETED.toString())) {
		Complaint complaint = complaintRepository.findById(id).get();
		complaint.setStatus(Status.REOPEN);
		complaintRepository.save(complaint);
		return true;

		}else {
			
			return false;
		}
		
	}
	
	@Override
	public int likeComplaintById(Integer id) {
		
		int count = complaintRepository.getLikeCount(id);
		
		System.out.println(count);
		
	 int ncount = count + 1;
		System.out.println("new " + ncount);
		complaintRepository.likeComplaint(ncount, id);
		return ncount;
	}

	@Override
	public List<Comments> viewAllComments() {
		return commentRepository.findAll();
	}
	
	@Override
	public void addComment(Comments comments) {
		Comments newComments = commentRepository.save(comments);
		System.out.println("------"+newComments.getCommentId());
//		Complaint complaint = complaintRepository.findByCommentsId(newComments.getCommentId()).get();
		Complaint complaint = newComments.getComplaintId();
		if(complaint.getCommentCount()==null){
			complaint.setCommentCount(1);
		}
		else{
			complaint.setCommentCount(complaint.getCommentCount()+1);
		}
		
		complaintRepository.save(complaint);
	}
	@Override
	public void addAndRemoveLikes(int complaintId, int userId) {
		
		User users = userRepository.findUserByUserId(userId);
		
		Complaint complaint = complaintRepository.findById(complaintId).get();
		
		Likes like = likeRepository.findLikeByUserIdAndComplaintId(users, complaint);
		if (like == null) {
			Likes likes = new Likes(users, complaint, LocalDateTime.now());
			likeRepository.save(likes);
			if(complaint.getLikeCount()!=null)
				complaint.setLikeCount(complaint.getLikeCount()+1);
			
			else
				complaint.setLikeCount(1);
			
		} else {
			likeRepository.delete(like);
			complaint.setLikeCount(complaint.getLikeCount()-1)	;
		}
		complaintRepository.save(complaint);
	}
	
	@Override
	public String addRatingToComplaint(int complaintId, int userId, int rating) {

		User user = userRepository.findUserByUserId(userId);
		Complaint complaint = complaintRepository.getById(complaintId);
		Ratings ratings = ratingRepository.findByUserIdAndComplaintId(user, complaint);
		if (ratings == null) {
			Ratings newRatings = new Ratings();
			newRatings.setRating(rating);
			newRatings.setUserId(user);
			newRatings.setComplaintId(complaint);
			newRatings.setRatingTimestamp(LocalDateTime.now());
			ratingRepository.save(newRatings);
			System.out.println("in if");
			return "Complaint has been rated";
		}
		System.out.println("after if");
		ratings.setRating(rating);
		ratingRepository.save(ratings);
		return "rating updated";
	}
	@Override
	public List<Ratings> viewAllRatings() {
		return ratingRepository.findAll();
	}
	@Override
	public List<Ratings> viewAllRatingsByCompliantId(int complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId).get();
		return ratingRepository.findAllByComplaintId(complaint);
	}
	@Override
	public List<Likes> viewAllLikes() {
		return likeRepository.findAll();
	}
	@Override
	public List<Likes> viewAllLikesByComplaintId(int complaintId) {
		Complaint complaint = complaintRepository.findById(complaintId).get();
		return likeRepository.findAllByComplaintId(complaint);
	}
	//count all likes on a complaint
	@Override
	public int getAllLikes(Integer complaintId) {		
		Complaint complaint = complaintRepository.getById(complaintId);
		System.out.println(1);
		List<Likes> likeList = likeRepository.findByComplaintId(complaint);
		System.out.println(2);
		if(complaint != null &&  !likeList.isEmpty() ){
			System.out.println(3);
			return  likeRepository.findLikeCountByComplaintNo(complaintId);	
		}
		return 0;
	}
	@Override
	public boolean LikedOrNot(int complaintId,int userId) {

		User user = userRepository.findUserByUserId(userId);
		
		Complaint complaint = complaintRepository.findById(complaintId).get();
		if(likeRepository.findLikeByUserIdAndComplaintId(user, complaint)!=null)
			return true;
		return false;
	}
}
