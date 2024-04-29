package com.freelancer.assetmanagement.dto;

import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ITAssetDto extends BaseModel{


	private long id;
	private String hostName;
	private String totalPort;
	private String managementPortInfo;
	private String defaultGateWay;
	private String firewallType;
	private String firewallIpAddress;
	private String macAddress;
	private String serviceTag;
	private String os;
	private String processor;
	private String raidCard;
	private String harddisk;
	private String networkCard;
	private String smps;
	private String vmtype;
	private String diskDetails;
	private String graphicsCard;
	private Boolean isPrinterLinked;
	private Boolean active;
	
	private Asset asset;
}
