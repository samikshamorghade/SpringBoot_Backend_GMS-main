package com.cybage.controller;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dao.ComplaintRepository;
import com.cybage.dao.DepartmentRepository;
import com.cybage.dto.CommentDto;
import com.cybage.dto.HodDto;
import com.cybage.dto.RestResponse;
import com.cybage.exception.NotFoundException;
import com.cybage.model.Comments;
import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.Status;
import com.cybage.model.User;
import com.cybage.service.IAdminService;
import com.cybage.service.IHodService;
import com.cybage.service.IUserService;

@RestController
@RequestMapping("/hod")
@CrossOrigin(origins = "http://localhost:4200")
public class HodController {
	@Autowired
	IHodService hodService;
	
	@Autowired 
	IUserService userService;
	
	@Autowired
	IAdminService adminService;
	
	@Autowired 
	DepartmentRepository departmentRepository;
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@GetMapping("/getAllComplaints/{departmentName}")
	public ResponseEntity<?> getAllComplaints(@PathVariable String departmentName){
		Department department = departmentRepository.findDepartmentByDepartmentName(departmentName);
		
		List<Complaint> list = hodService.findComplaintByDepartmentId(department);
		if(list.isEmpty()) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<>(new RestResponse((Object)department,(Object)list), HttpStatus.OK);
	}
	
	
	@GetMapping("/transferComplaint/{complaintId}/{departmentId}")

	public ResponseEntity<?> transferComplaint(@PathVariable Integer complaintId, @PathVariable Integer departmentId  ){
		
		hodService.transferComplaint(complaintId, departmentId);
		return new ResponseEntity<>("Sucessfullly transfer Complaint", HttpStatus.OK);		
	}
	
	@GetMapping("/changePassword/{userId}/{newPassword}")
	public ResponseEntity<String> changePassword(@PathVariable Integer userId,@PathVariable String newPassword){
		return (userService.changePassword(newPassword, userId))
				?new ResponseEntity<String>("Successfully Changed!!",HttpStatus.OK)
				:new ResponseEntity<String>("Failed to change!Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/getReminderComplaints/{departmentName}")
	public ResponseEntity<List<Complaint>> getReminderComplaints(@PathVariable String departmentName){
			
		List<Complaint> list = hodService.getComplaintByComplaintReminder(departmentName);
		if(list.isEmpty()) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/completeComplaint/{complaintId}")
	public ResponseEntity<?> completeComplaint(@PathVariable Integer complaintId){
		Complaint complaint = complaintRepository.findById(complaintId).get();
		//System.out.println(complaint.getComplaintDescription());
		complaint.setStatus(Status.COMPLETED);
		complaint.setComplaintReminder(false);
		complaint.setComplaintResolveDate(LocalDate.now());
		complaintRepository.save(complaint);
		return new ResponseEntity<>("Sucessfullly Complaint Completed", HttpStatus.OK);	
	}
	@GetMapping("/getAllDepartment")
	public ResponseEntity<List<Department>> getAllDepartment(){
		List<Department> list = adminService.getAllDepartment();
		if(list.isEmpty()) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/getComplaintById/{complaintId}")
	public ResponseEntity<Complaint> getComplaintById(@PathVariable Integer complaintId){
		Complaint complaint = complaintRepository.findById(complaintId).get();	
		if(complaint==null) {
			throw new NotFoundException("No record found");
		}
		return new ResponseEntity<Complaint>(complaint, HttpStatus.OK);
	}
	
}
