package com.freelancer.assetmanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.freelancer.assetmanagement.enumarated.AssetType;
import com.freelancer.assetmanagement.enumarated.Category;
import com.freelancer.assetmanagement.enumarated.OperationalStatus;
import com.freelancer.assetmanagement.enumarated.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Asset extends BaseModel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7746799155127815076L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne
	@JoinColumn
//	@JsonManagedReference
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
//	@JsonManagedReference
	private Organization organization;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
