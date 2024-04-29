package com.freelancer.assetmanagement.dto;

import lombok.Data;

@Data
public class AssetCount {

	private int activeCount;
	private int inactiveCount;
	private int totalCount;
	private double fixedAssetCost;
	private double iTAssetCost;
}
