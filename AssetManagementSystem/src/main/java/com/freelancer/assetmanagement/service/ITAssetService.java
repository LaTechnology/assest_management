package com.freelancer.assetmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.freelancer.assetmanagement.dto.ITAssetDto;

@Service
public interface ITAssetService {

	public ITAssetDto saveITAsset(ITAssetDto iTAssetDto);

	public ITAssetDto updateITAsset(ITAssetDto iTAssetDto);

	public List<ITAssetDto> fetchAllITAssets();

	public ITAssetDto findITAssetById(long id);

	public String deleteITAssetById(long id);

	public String deleteITAssetByAssetId(long assetId);
	
	public double totalITAssetCost();

	public ITAssetDto findITAssetByAssetId(long assetId);

}
