package com.freelancer.assetmanagement.model;

import lombok.Data;

@Data
public class AssetWithFixedAsset {

	private Asset asset;
	private FixedAsset fixedAsset;
}
