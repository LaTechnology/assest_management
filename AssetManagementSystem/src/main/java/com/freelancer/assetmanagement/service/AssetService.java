package com.freelancer.assetmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancer.assetmanagement.dto.AssetDto;

import com.freelancer.assetmanagement.dto.AssetWithFixedAssetDto;
import com.freelancer.assetmanagement.dto.AssetWithITAssetDto;

@Service
public interface AssetService {

	public AssetDto saveAsset(AssetDto assetDto);

	public AssetDto updateAsset(AssetDto assetDto);

	public List<AssetDto> fetchAllAssets();


	public AssetDto findAssetByAssetId(Long assetId);

	public String deleteAssetByAssetId(Long assetId);

	public String deleteAssetByEmployeeId(String employeeId);

	public List<AssetDto> fetchTotalAssets();


	public List<AssetDto> findAssetByEmployeeId(String employeeId);

	public AssetWithFixedAssetDto saveAssetWithFixedAsset(AssetWithFixedAssetDto assetWithFixedAssetDto);

	public AssetWithITAssetDto saveAssetWithITAsset(AssetWithITAssetDto assetWithITAssetDto);
}
