package com.cybage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.ComplaintRepository;
import com.cybage.dao.DepartmentRepository;
import com.cybage.dao.UserRepository;
import com.cybage.exception.NotFoundException;
import com.cybage.exception.ServerErrorException;
import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.Role;
import com.cybage.model.Status;
import com.cybage.model.User;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	

	@Override
	public void removeHodById(Integer userId) {
		User user = userRepository.findById(userId).get();
		Department department = departmentRepository.findDepartmentByHodId(user);
		if(department!=null) {
			department.setHodId(null);
			departmentRepository.save(department);
		}
		userRepository.deleteById(userId);
	}

	@Override
	public User getHodByDepartmentName(String departmentName) {
		return userRepository.findUserByDepartmentName(departmentName);
		
	}

	@Override
	public User getHodById(Integer userId) {
		return userRepository.findUserByUserRoleAndUserId(Role.DEPARTMENT_HEAD, userId);
	}

	@Override
	public User editHod(User user) {
		try {
			return userRepository.save(user);
		}
		catch(Exception exception) {
			throw new ServerErrorException("Failed to edit");
		}
		
		
	}
	@Override
	public List<User> getUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<User> getAllHod() {
		System.out.println(userRepository.findUserByUserRole(Role.DEPARTMENT_HEAD));
		return userRepository.findUserByUserRole(Role.DEPARTMENT_HEAD);
	}

	@Override
	public List<Complaint> getAllComplaint() {
		return complaintRepository.findAll();
	}


	@Override
	public boolean unlockAccount(String userName) {
		User user = userRepository.findByUserName(userName);
		user.setLoginAttempt(0);
		User unlockedUsers = userRepository.save(user);
		return (unlockedUsers!=null)?true:false;
	}

	@Override
	public List<User> getAllLockUser() {
		return userRepository.findByLoginAttemptGreaterThan(3);
		
	}
	
	@Override
	public List<Complaint> getPendingComplaint() {
		return complaintRepository.findComplaintByStatus(Status.PENDING);
	}

	@Override
	public List<Complaint> getCompletedComplaint() {
		return complaintRepository.findComplaintByStatus(Status.COMPLETED);
	}

	@Override
	public List<Complaint> getReopenComplaint() {
		return complaintRepository.findComplaintByStatus(Status.REOPEN);
	}

	@Override
	public List<User> getAvailableHod() {
		return userRepository.findUserByUserRoleAndDepartmentName(Role.DEPARTMENT_HEAD, null);
	}

	@Override
	public Department addDepartment(Department department) {
		try {
			return departmentRepository.save(department);
		} catch (Exception e) {
			throw new ServerErrorException("Failed to add");
		}
		
		
	}

	@Override
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();	
	}

	@Override
	public void removeDepartment(Integer departmentId) {
		departmentRepository.deleteById(departmentId);
		
	}

	@Override
	public Department editDepartment(Department department) {
		
		try {
			return departmentRepository.save(department);
		} catch (Exception e) {
			throw new ServerErrorException("Failed to add");
		}
	}

	@Override
	public Department getDepartmentbyId(Integer id) {
		return departmentRepository.findDepartmentByDepartmentId(id);
	}

	@Override
	public Department getDepartmentByDepartmentName(String departmentName) {
		return departmentRepository.findDepartmentByDepartmentName(departmentName);
	}
	
	

}
