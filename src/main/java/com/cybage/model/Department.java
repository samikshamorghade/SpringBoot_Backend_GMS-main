package com.cybage.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Integer departmentId;
	@Column(name = "department_name",unique = true)
	private String departmentName;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User hodId;
	
	@Column(name = "department_timestamp")
	private LocalDate departmentTimestamp;
	
	@OneToMany(mappedBy = "departmentId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Complaint>complaintList;
	
	public Department() {
		
	}

	public Department(Integer departmentId, String departmentName, User hodId, LocalDate departmentTimestamp,
			List<Complaint> complaintList) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.hodId = hodId;
		this.departmentTimestamp = departmentTimestamp;
		this.complaintList = complaintList;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public User getHodId() {
		return hodId;
	}

	public void setHodId(User hodId) {
		this.hodId = hodId;
	}

	public LocalDate getDepartmentTimestamp() {
		return departmentTimestamp;
	}

	public void setDepartmentTimestamp(LocalDate departmentTimestamp) {
		this.departmentTimestamp = departmentTimestamp;
	}

	public List<Complaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<Complaint> complaintList) {
		this.complaintList = complaintList;
	}

	

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", hodId=" + hodId
				+ ", departmentTimestamp=" + departmentTimestamp + ", complaintList=" + complaintList + "]";
	}
	 
	
	
}
