package com.freelancer.assetmanagement.enumarated;

import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ItAsset extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1041843706675430586L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToOne
	private Asset asset;
}
