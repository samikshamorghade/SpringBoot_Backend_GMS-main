package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.dao.ComplaintRepository;
import com.cybage.dao.DepartmentRepository;
import com.cybage.model.Complaint;
import com.cybage.model.Department;

@Service
public class HodServiceImpl implements IHodService {
	@Autowired
	ComplaintRepository complainRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	

	@Override
	public List<Complaint> findComplaintByDepartmentName(String departmentName) {
	Department department = departmentRepository.findDepartmentByDepartmentName(departmentName);
	
		return department.getComplaintList();
	}

	@Override
	public List<Complaint> findComplaintByDepartmentId(Department departmentId) {
		
		return complainRepository.findComplaintByDepartmentId(departmentId);
	}

	@Override
	public void transferComplaint(Integer complaintId, Integer departmentId) {
		Complaint complaint = complainRepository.findById(complaintId).get();
		Department newDepartment = departmentRepository.findById(departmentId).get();
		
		complaint.setDepartmentId(newDepartment);
		complainRepository.save(complaint);
		
	}

	@Override
	public List<Complaint> getComplaintByComplaintReminder(String departmentName) {
		Department department = departmentRepository.findDepartmentByDepartmentName(departmentName);
		return complainRepository.findComplaintByComplaintReminderAndDepartmentId(true, department);
	}


}
