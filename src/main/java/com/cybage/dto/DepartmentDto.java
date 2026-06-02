package com.cybage.dto;

public class DepartmentDto {
	 private String departmentName;
	 private Integer addHod;
	 public DepartmentDto() {
		
	}
	public DepartmentDto(String departmentName, Integer addHod) {
		super();
		this.departmentName = departmentName;
		this.addHod = addHod;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getAddHod() {
		return addHod;
	}
	public void setAddHod(Integer addHod) {
		this.addHod = addHod;
	}
	@Override
	public String toString() {
		return "DepartmentDto [departmentName=" + departmentName + ", addHod=" + addHod + "]";
	}
	
}
