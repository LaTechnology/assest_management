package com.freelancer.assetmanagement.dto;

import com.freelancer.assetmanagement.enumarated.AssetType;
import com.freelancer.assetmanagement.enumarated.Category;
import com.freelancer.assetmanagement.enumarated.OperationalStatus;
import com.freelancer.assetmanagement.enumarated.Status;
import com.freelancer.assetmanagement.model.BaseModel;
import com.freelancer.assetmanagement.model.Employee;
import com.freelancer.assetmanagement.model.Organization;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AssetDto extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9090913997526712273L;
	private long assetId;
	private String assetCode;
	private String assetImage;
	private String assetName;
	private String usedBy;
	private String baseLocation;
	private String remark;
	private String serialNumber;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.STRING)
	private OperationalStatus operationalStatus;

	@Enumerated(EnumType.STRING)
	private AssetType assetType;
	@Enumerated(EnumType.STRING)
	private Category category;
	private double cost;
	private String brand;
	private String modelNumber;
	private Boolean active;
	
	
	
//	@OneToOne
//	private AssetCategory assetCategoryId;
	
//	@ManyToOne
//	@JoinColumn
	private EmployeeData employeeData;
	
//	@ManyToOne
//	@JoinColumn
	private OrganizationData organizationData;

	
	
}
