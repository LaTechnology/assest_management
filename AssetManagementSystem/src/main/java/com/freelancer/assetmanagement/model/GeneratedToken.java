package com.freelancer.assetmanagement.model;

import org.springframework.stereotype.Component;

import com.freelancer.assetmanagement.dto.EmployeeDto;
import com.freelancer.assetmanagement.dto.OrganizationData;
import com.freelancer.assetmanagement.dto.OrganizationDto;

@Component
public class GeneratedToken {

	private String token;
//	private OrganizationDto organizationDto;
	private EmployeeDto employeeDto;
	
	
	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

//	public OrganizationDto getOrganizationDto() {
//		return organizationDto;
//	}
//
//	public void setOrganizationDto(OrganizationDto organizationDto) {
//		this.organizationDto = organizationDto;
//	}

	public GeneratedToken() {
		super();
	}

	public GeneratedToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "GeneratedToken [token=" + token + "]";
	}

	
}

