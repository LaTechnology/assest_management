package com.freelancer.assetmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancer.assetmanagement.dto.FixedAssetDto;
import com.freelancer.assetmanagement.dto.ITAssetDto;

@Service
public interface FixedAssetService {

	public FixedAssetDto saveFixedAsset(FixedAssetDto fixedAssetDto);

	public FixedAssetDto updateFixedAsset(FixedAssetDto fixedAssetDto);

	public List<FixedAssetDto> fetchAllFixedAssets();

	public FixedAssetDto findFixedAssetById(long id);

	public String deleteFixedAssetById(long id);

	public String deleteFixedAssetByAssetId(long assetId);
	
	public double totalFixedAssetCost();

	public FixedAssetDto findFixedAssetByAssetId(long assetId);
	
}
