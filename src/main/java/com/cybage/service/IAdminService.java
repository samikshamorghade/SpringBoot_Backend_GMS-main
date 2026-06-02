package com.cybage.service;

import java.util.List;
import java.util.Optional;

import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.User;

public interface IAdminService {
	
	 void removeHodById(Integer userId);

	 User getHodByDepartmentName(String departmentName);

	 User getHodById(Integer userId);

	 User editHod(User user);
	
	 List<User> getUserByName(String name);
	
	 List<User> getAllHod();
	
	 List<Complaint> getAllComplaint();
	
	 List<Complaint> getPendingComplaint();
	 
	 List<Complaint> getCompletedComplaint();
	 
	 List<Complaint> getReopenComplaint();
	 
	 boolean unlockAccount(String userName);
	
	 List<User> getAllLockUser();
	 
	 List<User> getAvailableHod();
	 
	 Department addDepartment(Department department);
	 
	 List<Department> getAllDepartment();

	 void removeDepartment(Integer departmentId);
	 
	 Department editDepartment(Department department);
	 
	 Department getDepartmentbyId(Integer id);
	
	 Department getDepartmentByDepartmentName(String departmentName);
	

}
