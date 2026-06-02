package com.cybage.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cybage.dto.CommentDto;
import com.cybage.dto.DepartmentDto;
import com.cybage.dto.HodDto;
import com.cybage.dto.LikeDto;
import com.cybage.dto.RatingDto;
import com.cybage.dto.RestResponse;
import com.cybage.exception.NotFoundException;
import com.cybage.exception.ServerErrorException;
import com.cybage.model.Comments;
import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.Likes;
import com.cybage.model.Ratings;
import com.cybage.model.Role;
import com.cybage.model.User;
import com.cybage.service.IAdminService;
import com.cybage.service.IUserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@Autowired
	IUserService userService;

	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String host;

	public AdminController() {
		System.out.println("in adminController constr");
	}

	@GetMapping("/getAllComplaints")
	public ResponseEntity<List<Complaint>> getAllComplaints() {
		List<Complaint> list = adminService.getAllComplaint();
		if (list.size() == 0) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<List<Complaint>>(list, HttpStatus.OK);
	}

	@PostMapping("/addHod")
	public ResponseEntity<?> addHod(@RequestBody HodDto hodDto) {

		try {
			User user = new User(hodDto.getHodName(), hodDto.getEmail(), hodDto.getHodUserName(), hodDto.getPassword(),
					hodDto.getHodMobileNumber(), hodDto.getHodAddress(), LocalDate.now());
			user.setUserRole(Role.DEPARTMENT_HEAD);
			User newUser = userService.addUser(user);
			if (newUser == null) {
				throw new ServerErrorException("Failed to add");
			}
			SimpleMailMessage message = new SimpleMailMessage();

			message.setFrom(host);
			message.setTo(user.getEmailId());
			message.setSubject("Welcome to Pune Municipal Corporation");
			message.setText("Hello " + user.getName() + ","
					+ "\n\nYour login credentials are given below: \n\nUsername: " + user.getUserName() + "\nPassword: "
					+ user.getPassword() + "\n\nPlease try to login with your Username and Password.");
			javaMailSender.send(message);

			return new ResponseEntity<>(new RestResponse((Object) newUser, "Sucessfully Added"), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ServerErrorException("Failed to add");
		}
	}

	@DeleteMapping("/removeHod/{id}")
	public ResponseEntity<String> removeHodById(@PathVariable Integer id) {
		adminService.removeHodById(id);
		return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
	}

	@GetMapping("/getHod/{id}")
	public ResponseEntity<User> getHodById(@PathVariable Integer id) {
		User user = adminService.getHodById(id);
		if (user == null) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/getHodByDepartmentName/{departmentName}")
	public ResponseEntity<User> getHodByDepartmentName(@PathVariable String departmentName) {
		User user = adminService.getHodByDepartmentName(departmentName);
		if (user == null) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("/editHod")
	public ResponseEntity<?> editHod(@RequestBody HodDto hodDto) {
		User user = userService.getUserByUserId(hodDto.getHodId());
		user.setName(hodDto.getHodName());
		user.setEmailId(hodDto.getEmail());
		user.setPassword(hodDto.getPassword());
		user.setUserName(hodDto.getHodUserName());
		user.setUserMobileNumber(hodDto.getHodMobileNumber());
		user.setUserAddress(hodDto.getHodAddress());
		userService.addUser(user);

		return new ResponseEntity<>(new RestResponse((Object) user, "Sucessfully edited"), HttpStatus.OK);
	}

	@GetMapping("/getAllHod")
	public ResponseEntity<List<User>> getAllHod() {
		List<User> list = adminService.getAllHod();
		if (list.size() == 0) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("/unlockAccount/{userId}")
	public ResponseEntity<?> unlockAccount(@PathVariable int userId) {
		User unlockUser = userService.getUserByUserId(userId);
		System.out.println(unlockUser.getName());
		int loginAttempt = unlockUser.getLoginAttempt();
		if (loginAttempt > 3) {
			unlockUser.setLoginAttempt(0);
			userService.addUser(unlockUser);
			return new ResponseEntity<>(new RestResponse((Object) unlockUser, "Sucessfully Unlock"), HttpStatus.OK);
		} else {
			throw new ServerErrorException("Failed to unlock");
		}

	}

	@GetMapping("/getAllLockUser")
	public ResponseEntity<List<User>> getAllLockUser() {
		List<User> list = adminService.getAllLockUser();
		if (list.size() == 0) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("/getAvailableHod")
	public ResponseEntity<List<User>> getAvailableHod() {
		List<User> list = adminService.getAvailableHod();
		if (list.size() == 0) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PostMapping("/addDepartment/{id}")
	public ResponseEntity<?> addDepartment(@RequestBody Department department, @PathVariable int id) {
		User user = adminService.getHodById(id);
		department.setHodId(user);
		department.setDepartmentTimestamp(LocalDate.now());
		System.out.println(department);
		Department newDepartment = adminService.addDepartment(department);
		user.setDepartmentName(newDepartment.getDepartmentName());
		adminService.editHod(user);

		return new ResponseEntity<>(new RestResponse((Object) newDepartment, "Sucessfully Department Add"),
				HttpStatus.CREATED);
	}

	@GetMapping("/getAllDepartment")
	public ResponseEntity<List<Department>> getAllDepartment() {
		List<Department> list = adminService.getAllDepartment();
		if (list.size() == 0) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@DeleteMapping("/removeDepartment/{id}")
	public ResponseEntity<String> removeDepartmentById(@PathVariable Integer id) {
		adminService.removeDepartment(id);
		return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
	}

	@PutMapping("/editDepartment")
	public ResponseEntity<?> editDepartment(@RequestBody DepartmentDto departmentDto) {
		// User user= userService.getUserByUserId(hodDto.getHodId());
		// user.setName(hodDto.getHodName());
		// user.setEmailId(hodDto.getEmail());
		// user.setPassword(hodDto.getPassword());
		// user.setUserName(hodDto.getHodUserName());
		// user.setUserMobileNumber(hodDto.getHodMobileNumber());
		// user.setUserAddress(hodDto.getHodAddress());
		// userService.addUser(user);
		// return new ResponseEntity<>(new
		// RestResponse((Object)user,"Sucessfully edited"), HttpStatus.OK);

		Department department = adminService.getDepartmentByDepartmentName(departmentDto.getDepartmentName());
		User oldHod = department.getHodId();
		oldHod.setDepartmentName(null);
		adminService.editHod(oldHod);
		User user = adminService.getHodById(departmentDto.getAddHod());
		user.setDepartmentName(departmentDto.getDepartmentName());

		department.setHodId(adminService.editHod(user));

		Department editDepartment = adminService.addDepartment(department);
		return new ResponseEntity<>(new RestResponse((Object) editDepartment, "Sucessfully edited "), HttpStatus.OK);
	}

	@GetMapping("/getDepartment/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
		Department department = adminService.getDepartmentbyId(id);
		System.out.print(department);
		if (department == null) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	@GetMapping("/getLikesByComplaint/{complaintId}")
	public ResponseEntity<LikeDto> getLikesByComplaint(@PathVariable Integer complaintId) {

		Complaint complaint = userService.getComplaintById(complaintId);
		List<Likes> likes = userService.getLikeByComplaintId(complaintId);

		LikeDto likesDto = new LikeDto(complaint.getComplaintId(), complaint.getComplaintDescription(),
				complaint.getLikeCount());
		likes.forEach(like -> {
			likesDto.getUser().add(like.getUserId().getName());
			likesDto.getTimeStamp().add(like.getLikeTimestamp());
		});
		return new ResponseEntity<LikeDto>(likesDto, HttpStatus.OK);

	}

	@GetMapping("/getCommentByComplaint/{complaintId}")
	public ResponseEntity<CommentDto> getCommentByComplaint(@PathVariable Integer complaintId) {

		Complaint complaint = userService.getComplaintById(complaintId);
		List<Comments> comment = userService.getCommentByComplaintId(complaintId);

		CommentDto commentDto = new CommentDto(complaint.getComplaintId(), complaint.getComplaintDescription(),
				complaint.getCommentCount());
		comment.forEach(comments -> {
			commentDto.getUser().add(comments.getUserId().getName());
			commentDto.getTimeStamp().add(comments.getCommentTimestamp());
			commentDto.getComment().add(comments.getComment());
		});
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);

	}

	@GetMapping("/getRatingByComplaint/{complaintId}")
	public ResponseEntity<RatingDto> getRatingByComplaint(@PathVariable Integer complaintId) {

		Complaint complaint = userService.getComplaintById(complaintId);
		List<Ratings> complaintRating = userService.getRatingByComplaintId(complaintId);

		System.out.println(complaintRating);
		RatingDto ratingDto = new RatingDto(complaint.getComplaintId(), complaint.getComplaintDescription(),
				complaintRating);
		System.out.println(ratingDto);

		return new ResponseEntity<RatingDto>(ratingDto, HttpStatus.OK);

	}

	@GetMapping("/getDepartmentByDepartmentName/{departmentName}")
	public ResponseEntity<Department> getUserByUserName(@PathVariable String departmentName) {

		Department department = adminService.getDepartmentByDepartmentName(departmentName);
		if (department != null) {
			return new ResponseEntity<>(department, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getCommentsByComplaintId/{complaintId}")
	public ResponseEntity<List<Comments>> getCommentsByComplaintId(@PathVariable Integer complaintId) {

		Complaint complaint = userService.getComplaintById(complaintId);

		List<Comments> comments = userService.getCommentByComplaintId(complaintId);

		return new ResponseEntity<List<Comments>>(comments, HttpStatus.OK);

	}

	@GetMapping("/getLikesByComplaintId/{complaintId}")
	public ResponseEntity<List<Likes>> getLikesByComplaintId(@PathVariable Integer complaintId) {

		Complaint complaint = userService.getComplaintById(complaintId);

		List<Likes> likes = userService.getLikeByComplaintId(complaintId);

		return new ResponseEntity<List<Likes>>(likes, HttpStatus.OK);

	}

	@GetMapping("/getFeedbacks")
	public ResponseEntity<List<Object>> getFeedbacks() {

		String uri = "http://localhost:7070/feedback/getAllFeedbacks";
		RestTemplate restTemplate = new RestTemplate();
		Object[] feedbacks = restTemplate.getForObject(uri, Object[].class);
		List<Object> feedbackList = Arrays.asList(feedbacks);
		if (feedbackList.isEmpty())
			throw new NotFoundException("No records found!!");
		return new ResponseEntity<>(feedbackList, HttpStatus.OK);
	}
}
