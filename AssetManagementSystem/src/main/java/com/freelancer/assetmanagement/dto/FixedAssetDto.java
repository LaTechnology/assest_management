package com.freelancer.assetmanagement.dto;

import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.BaseModel;

import lombok.Data;

@Data
public class FixedAssetDto extends BaseModel{

	private long id;
	private String color;
	private String graphicsCard;
	private String ram;
	private String rom;
	private String processor;
	private String material;
	
//	@Enumerated(EnumType.STRING)
	private String os ;
	private String osVersion;
	private String battery;
	private String chargerType;
	private String wireless;
	private String weight;
	private String dimension;
	private String ipaddress;
	private String connectorType;
	private String blutoothVersion;
	private String chargingTime;
	private String capacity;
	private String size;
	private String watts;
	private String volt;
	private String length;
	private String simNumber;
	private String imeiNumber;
	private String generation;
	private Boolean active;
	
	private Asset asset;
}
