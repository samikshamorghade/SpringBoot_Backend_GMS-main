package com.cybage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybage.model.Complaint;
import com.cybage.model.Role;
import com.cybage.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findUserByUserId(int id);
	List<User> findByName(String name);
	
	User findByUserName(String userName);
	
	User findUserByUserNameAndPassword(String userName, String password);
	
	List<User> findByLoginAttemptGreaterThan(int loginAttempts);
	
	
    List<User>findUserByUserRole(Role userRole);
    
    User findUserByUserRoleAndUserId(Role userRole, Integer id);

	User findUserByDepartmentName(String departmentName);
	
	List<User> findUserByUserRoleAndDepartmentName(Role userRole, String departmentName);
	
	User findUserByUserName(String userName);
	
	
}
