package com.cybage.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dao.DepartmentRepository;
import com.cybage.dto.CitizenDto;
import com.cybage.dto.DisplayCommentDto;
import com.cybage.model.Comments;
import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.Likes;
import com.cybage.model.Ratings;
import com.cybage.model.Role;
import com.cybage.model.Status;
import com.cybage.model.User;
import com.cybage.service.CitizenServiceImpl;
import com.cybage.service.UserServiceImpl;

@RestController
@RequestMapping("/citizen")
@CrossOrigin(origins = "http://localhost:4200")
public class CitizenController {
	@Autowired
	CitizenServiceImpl  citizenServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@GetMapping("/getAllComplaints/{userId}")
	 public List<Complaint> getAllComplaintsById(@PathVariable int userId)
	 {
		List<Complaint> complaints = citizenServiceImpl.viewAllComplaintsById(userId);
		
		return complaints;
	 }
	
	@PostMapping("/makeComplaint/{userId}/{departmentId}/{complaintDesc}")
	public ResponseEntity<String> makeComplaint(@PathVariable Integer userId,@PathVariable Integer departmentId,@PathVariable String complaintDesc)	{
		Complaint complaint= new Complaint();
		
		User user = userServiceImpl.getUserByUserId(userId);
		Department department = departmentRepository.findById(departmentId).get();
		complaint.setComplaintDescription(complaintDesc);
		complaint.setDepartmentId(department);
		complaint.setUserId(user);
		complaint.setComplaintRegisterDate(LocalDate.now());
		complaint.setStatus(Status.PENDING);
		citizenServiceImpl.makeComplaint(complaint);
		return new ResponseEntity<String>("ADDED Complaint !!",HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllComplaints")
	 public List<Complaint> getAllComplaints()	 {		
		
		List<Complaint> complaints = citizenServiceImpl.viewAllComplaints();		
		return complaints;
	 }
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody CitizenDto citizenDto)	{		
		User user = new User(citizenDto.getName(), citizenDto.getUserEmail(), citizenDto.getUsername(), citizenDto.getPassword(), citizenDto.getUserMobileNumber(), citizenDto.getUserAddress(), LocalDate.now());
		
		user.setUserRole(Role.CITIZEN);
		userServiceImpl.addUser(user);
		return new ResponseEntity<String>("ADDED CITIZEN !!",HttpStatus.CREATED);
	}
	
	
	@GetMapping("/changePassword/{id}/{newPassword}")
	public ResponseEntity<?> changePassword(@PathVariable Integer id,@PathVariable String newPassword){
		userServiceImpl.changePassword(newPassword, id);
		return new ResponseEntity<>(newPassword,HttpStatus.CREATED);
	}
	
	@GetMapping("/enableComplaintReminder/{id}")
	public void enableComplaintReminder(@PathVariable Integer id) {		
		citizenServiceImpl.enableComplaintReminder(id);		
	}
	
//	@GetMapping("/reopenComplaint/{id}")
//	public void reopenComplaint(@PathVariable int id) {
//		citizenServiceImpl.reopenComplaint(id);
//	}
	
	@GetMapping("/reopenComplaint/{id}")
	public void reopenComplaint(@PathVariable int id) {		
		citizenServiceImpl.reopenComplaint(id);
	}
	
	@GetMapping("/getStatusById/{id}")
	public String getStatusById(@PathVariable int id) {
		return	citizenServiceImpl.getStatusById(id);		
	}
	
	@GetMapping("/changeStatusReopen/{id}")
	public ResponseEntity<?> changeStatusReopen(@PathVariable int id){
		citizenServiceImpl.changeStatusReopen( id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/likeById/{id}/{userId}")
	public ResponseEntity<Boolean> likeComplaint(@PathVariable Integer id,@PathVariable Integer userId) {
		citizenServiceImpl.addAndRemoveLikes(id,userId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
//	public ResponseEntity<Boolean> addAndRemoveLike(@RequestParam int userId, @RequestParam int batchId) {
//		userService.addAndRemoveLikes(userId, batchId);
//		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//	}

	
	@GetMapping("/getAllComments")
	 public List<Comments> getAllComments(){
		//System.out.println(citizenServiceImpl.viewAllComplaints());
		List<Comments> comments = citizenServiceImpl.viewAllComments();		
		return comments;
	 }
	
	@PostMapping("/addComments")
	public ResponseEntity<?> addComments(@RequestParam int complaintId, @RequestParam int userId, @RequestParam String comment)
	{
		System.out.println("------"+complaintId+"------"+userId+"-------"+comment);
		Comments comments = new Comments();
		comments.setComplaintId(userServiceImpl.getComplaintById(complaintId));
		comments.setUserId(userServiceImpl.getUserByUserId(userId));
		comments.setComment(comment);
		comments.setCommentTimestamp(LocalDateTime.now());
		citizenServiceImpl.addComment(comments);
		return new ResponseEntity<>(comments,HttpStatus.CREATED);
	}
	
	@GetMapping("/getCommentByComplaint/{complaintId}")
	public ResponseEntity<?> getCommentByComplaint(@PathVariable Integer complaintId) {

		Complaint complaint = userServiceImpl.getComplaintById(complaintId);
		List<Comments> commentList = userServiceImpl.getCommentByComplaintId(complaintId);
		
		List<DisplayCommentDto> commentDtoList = new ArrayList<>();
		commentList.forEach((comment)->{
			commentDtoList.add(new DisplayCommentDto(complaintId,comment.getComment(),comment.getUserId().getUserName(),
					comment.getCommentTimestamp()));
		});
		
		return new ResponseEntity<>(commentDtoList, HttpStatus.OK);

	}
	
	@PostMapping("/giveRatingById")
	public ResponseEntity<Boolean> rateComplaint(@RequestParam int complaintId, @RequestParam int userId,
			@RequestParam int rating) {
		citizenServiceImpl.addRatingToComplaint(complaintId, userId, rating);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping("/getAllRatings")
	public List<Ratings> getAllRatings() {
		// System.out.println(citizenServiceImpl.viewAllComplaints());
		List<Ratings> ratings = citizenServiceImpl.viewAllRatings();
		// System.out.println(complaints);
		return ratings;
	}
	
	@GetMapping("/getAllRatingsByComplaintId/{complaintId}")
	public ResponseEntity<?> getAllRatingsByComplaintId(@PathVariable int complaintId) {
		List<Ratings> ratings = citizenServiceImpl.viewAllRatingsByCompliantId(complaintId);
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
	
	@GetMapping("/getAllLikes")
	public List<Likes> getAllLikes() {
		// System.out.println(citizenServiceImpl.viewAllComplaints());
		List<Likes> likes = citizenServiceImpl.viewAllLikes();
		// System.out.println(complaints);
		return likes;
	}
	
	@GetMapping("/getAllLikesByComplaintId/{complaintId}")
	public List<Likes> getAllLikesByComplaintId(@PathVariable int complaintId) {
		List<Likes> likes = citizenServiceImpl.viewAllLikesByComplaintId(complaintId);
		return likes;
	}
	
	/*@GetMapping("/getAllLikesCount/{complaintId}")
	public int countLikesByComplaintId(@PathVariable int complaintId) {
		// System.out.println(citizenServiceImpl.viewAllComplaints());
		int likes = citizenServiceImpl.countLikesByComplaintId(complaintId);
		// System.out.println(complaints);
		return likes;
	}
	*/
	@GetMapping("/getLikesCount/{complaintId}")
	public int getAllLikes(@PathVariable Integer complaintId) {
		int likes = citizenServiceImpl.getAllLikes(complaintId);

		return likes;
	}
	@GetMapping("/likedOrNot/{complaintId}/{userId}")
	public ResponseEntity<Boolean>  LikedOrNot(@PathVariable Integer complaintId,@PathVariable Integer userId) {
		return new ResponseEntity<Boolean>(citizenServiceImpl.LikedOrNot(complaintId,userId), HttpStatus.OK);	
	}

}

