package com.freelancer.assetmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelancer.assetmanagement.dto.AssetDto;
import com.freelancer.assetmanagement.dto.FixedAssetDto;
import com.freelancer.assetmanagement.dto.ITAssetDto;
import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.FixedAsset;
import com.freelancer.assetmanagement.model.ITAsset;
import com.freelancer.assetmanagement.repository.AssetRepository;
import com.freelancer.assetmanagement.repository.FixedAssetRepository;

@Service
@Transactional
public class FixedAssetServiceImp implements FixedAssetService{
	private static final boolean ACTIVE=true;

	Logger log=LoggerFactory.getLogger(FixedAssetServiceImp.class);
	@Autowired
	protected FixedAssetRepository fixedAssetRepository;
	
	@Autowired
	protected AssetRepository assetRepository;
	
	@Override
	public FixedAssetDto saveFixedAsset(FixedAssetDto fixedAssetDto) {
		FixedAsset fixedAsset = new FixedAsset();
		fixedAsset.setActive(ACTIVE);
		BeanUtils.copyProperties(fixedAssetDto, fixedAsset);
		FixedAsset save = fixedAssetRepository.save(fixedAsset);
		
		fixedAssetDto.setId(save.getId());
		fixedAssetDto.setCreatedAt(save.getCreatedAt());
		fixedAssetDto.setCreatedBy(save.getCreatedBy());
		fixedAssetDto.setModifiedAt(save.getModifiedAt());
		fixedAssetDto.setModifiedBy(save.getModifiedBy());
		return fixedAssetDto;
	}
	
	@Override
	public FixedAssetDto updateFixedAsset(FixedAssetDto fixedAssetDto) {
		FixedAsset findByAssetId = fixedAssetRepository.findByIdAndActive(fixedAssetDto.getId(), ACTIVE);
		if (findByAssetId != null) {
			fixedAssetDto.setId(findByAssetId.getId());
			fixedAssetDto.setCreatedAt(findByAssetId.getCreatedAt());
			fixedAssetDto.setCreatedBy(findByAssetId.getCreatedBy());
			BeanUtils.copyProperties(fixedAssetDto, findByAssetId);

			FixedAsset updatedAsset = fixedAssetRepository.save(findByAssetId);

			fixedAssetDto.setModifiedAt(updatedAsset.getModifiedAt());
			fixedAssetDto.setModifiedBy(updatedAsset.getModifiedBy());
			return fixedAssetDto;
		} else {
			log.info("Fixed Asset not found so update is not possible");
			return null;
		}
	}
	@Override
	public List<FixedAssetDto> fetchAllFixedAssets() {
		List<FixedAssetDto> assetDtos = new ArrayList<FixedAssetDto>();

//		List<Employee> employees = assetRepository.findAll();
		List<FixedAsset> assets = fixedAssetRepository.findByActive(ACTIVE);
		for (FixedAsset asset : assets) {
			FixedAssetDto assetDto = new FixedAssetDto();
			BeanUtils.copyProperties(asset, assetDto);
			assetDtos.add(assetDto);

		}
		return assetDtos;
	}
	@Override
	public FixedAssetDto findFixedAssetById(long id) {
		FixedAssetDto assetDto = new FixedAssetDto();
		FixedAsset fixedAsset = fixedAssetRepository.findByIdAndActive(id, ACTIVE);
		BeanUtils.copyProperties(fixedAsset, assetDto);
		return assetDto;
	}
	@Override
	public String deleteFixedAssetById(long id) {
		FixedAsset findByAssetId = fixedAssetRepository.findByIdAndActive(id, ACTIVE);
		if (findByAssetId != null) {
//			employeeRepository.delete(findByEmployeeId);
			findByAssetId.setActive(!ACTIVE);
			fixedAssetRepository.save(findByAssetId);
			return "deleted Successfully";
		} else {
			log.info("Fixed Asset not found so delete is not possible");
			return "Fixed Asset not found to delete";
		}
	}
	@Override
	public String deleteFixedAssetByAssetId(long assetId) {
		Asset findByAssetIdAndActive = assetRepository.findByAssetIdAndActive(assetId, ACTIVE);
		FixedAsset findByAssetId = fixedAssetRepository.findByAssetAndActive(findByAssetIdAndActive, ACTIVE);
		if (findByAssetId != null) {
//			employeeRepository.delete(findByEmployeeId);
			findByAssetId.setActive(!ACTIVE);
			fixedAssetRepository.save(findByAssetId);
			return "deleted Successfully";
		} else {
			log.info("Fixed Asset not found so delete is not possible");
			return "Fixed Asset not found to delete";
		}
	}

	@Override
	public double totalFixedAssetCost() {
		double cost=0;
		log.info("Inside Totalfixedcost method in FixedAssetImp class");
		List<FixedAsset> findByActive = fixedAssetRepository.findByActive(ACTIVE);
		if(Objects.nonNull(findByActive)) {
			for(FixedAsset fixedAsset:findByActive) {
				cost+=fixedAsset.getAsset().getCost();
			}
			return cost;
		}
		return 0;
	}
	
	@Override
	public FixedAssetDto findFixedAssetByAssetId(long assetId) {

		Asset findByAssetIdAndActive = assetRepository.findByAssetIdAndActive(assetId, ACTIVE);
		FixedAsset findByAssetId = fixedAssetRepository.findByAssetAndActive(findByAssetIdAndActive, ACTIVE);
		log.info("logging-> {}", findByAssetId);
		if (Objects.nonNull(findByAssetId)) {
			log.info("findFixedAssetByAssetId is present" );
			FixedAssetDto fixedAssetDto=new FixedAssetDto();
			BeanUtils.copyProperties(findByAssetId, fixedAssetDto);
			return fixedAssetDto;
		}
		log.info("findFixedAssetByAssetId is not present" );
		return null;
	}
	
}
