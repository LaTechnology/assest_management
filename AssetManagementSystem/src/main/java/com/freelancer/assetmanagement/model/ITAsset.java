package com.freelancer.assetmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ITAsset extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6388377988675249513L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToOne
	private Asset asset;
}
