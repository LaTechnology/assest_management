package com.freelancer.assetmanagement.model;

import com.freelancer.assetmanagement.enumarated.OS;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class FixedAsset extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 651131289480389403L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToOne
	private Asset asset;
}
