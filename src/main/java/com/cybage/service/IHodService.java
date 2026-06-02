package com.cybage.service;

import java.util.List;

import com.cybage.model.Complaint;
import com.cybage.model.Department;

public interface IHodService {

	List<Complaint> findComplaintByDepartmentName(String departmentName);

	List<Complaint> findComplaintByDepartmentId(Department departmentId);

	void transferComplaint(Integer complaintId, Integer departmentId);

	List<Complaint> getComplaintByComplaintReminder(String departmentName);

}
