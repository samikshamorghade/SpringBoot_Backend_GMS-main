package com.cybage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dao.UserRepository;
import com.cybage.dto.AuthenticateDto;
import com.cybage.dto.RestResponse;
import com.cybage.exception.NotFoundException;
import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.User;
import com.cybage.service.IUserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	IUserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String host;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/authenticateUser")
	public ResponseEntity<User> authenticateUser(@RequestBody AuthenticateDto authenticateDto) {
		String username = authenticateDto.getUserName();
		String password = authenticateDto.getPassword();

		if (username != null || !username.equals("")) {
			User user = userService.getUserByUserName(username);
			if (user != null) {
				int loginAttempt = user.getLoginAttempt();
				if (loginAttempt > 3) {
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				} else {
					User user2 = userRepository.findUserByUserNameAndPassword(username, password);
					if (user2 == null) {
						user.setLoginAttempt(user.getLoginAttempt() + 1);
						userRepository.save(user);
						return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
					} else {
						user.setLoginAttempt(0);
						userRepository.save(user);
						return new ResponseEntity<User>(user, HttpStatus.OK);
					}

				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getUserByUserName/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
		User user = userService.getUserByUserName(userName);
		if(user!=null) {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getotp/{email}")
	public ResponseEntity<String> sendOTP(@PathVariable String email) {

		return new ResponseEntity<String>(OTPEmail(email), HttpStatus.OK);
	}

	public String OTPEmail(String email) {
		System.out.println("Sending Email.....");
		String otp = "" + ((int) (Math.random() * 9000) * 100);
		SimpleMailMessage mesg = new SimpleMailMessage();

		mesg.setFrom(host);
		mesg.setTo(email);
		mesg.setSubject("Welcome to Pune Muncipal Corporation");
		mesg.setText("Your OTP for Login is  " + otp);
		emailSender.send(mesg);

		System.out.println("success");
		return otp;
	}
	@GetMapping("/getUserByUserId/{userId}")
	public ResponseEntity<User> getUserByUserName(@PathVariable Integer userId){
		User user = userRepository.findById(userId).get();
		if(user!=null) {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
