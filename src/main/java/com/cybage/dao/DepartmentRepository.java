package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.model.Department;
import com.cybage.model.User;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	Department findDepartmentByHodId(User user);
	Department findDepartmentByDepartmentId(Integer departmentId);
	Department findDepartmentByDepartmentName(String departmentName);
}
