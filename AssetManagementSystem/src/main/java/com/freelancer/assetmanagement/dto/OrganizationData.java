package com.freelancer.assetmanagement.dto;

import java.util.List;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.freelancer.assetmanagement.model.BaseModel;
import com.freelancer.assetmanagement.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class OrganizationData extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8144883624936477977L;
	private long organizationId;
	private String organizationCode;
	private String name;
	private String emailId;
	private String location;
	private String contactNumber;
	
//	private List<Asset> asset;
	
	private List<Employee> employees;

	@Override
	public String toString() {
		return "OrganizationData [organizationId=" + organizationId + ", organizationCode=" + organizationCode + ", name="
				+ name + ", emailId=" + emailId + ", location=" + location + ", contactNumber=" + contactNumber + "]";
	}
	
}
