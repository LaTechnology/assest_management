package com.freelancer.assetmanagement.model;

import org.springframework.stereotype.Component;

import com.freelancer.assetmanagement.dto.OrganizationData;
import com.freelancer.assetmanagement.dto.OrganizationDto;

@Component
public class AuthRequest {

	private String emailId;
	private String password;
	private OrganizationDto organizationDto;
	
	public AuthRequest() {
		super();
	}
	public AuthRequest(String emailId, String password, OrganizationDto organizationDto) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.organizationDto = organizationDto;
	}

	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public OrganizationDto getOrganizationDto() {
		return organizationDto;
	}


	public void setOrganizationDto(OrganizationDto organizationDto) {
		this.organizationDto = organizationDto;
	}


	@Override
	public String toString() {
		return "AuthRequest [emailId=" + emailId + ", password=" + password + ", organizationDto=" + organizationDto
				+ "]";
	}


	
}

