package com.freelancer.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AssetWithITAssetDto {

	private AssetDto assetDto;
	private ITAssetDto itAssetDto;
}
