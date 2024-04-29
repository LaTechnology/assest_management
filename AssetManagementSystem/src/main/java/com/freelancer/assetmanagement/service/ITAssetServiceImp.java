package com.freelancer.assetmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelancer.assetmanagement.dto.FixedAssetDto;
import com.freelancer.assetmanagement.dto.ITAssetDto;
import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.FixedAsset;
import com.freelancer.assetmanagement.model.ITAsset;
import com.freelancer.assetmanagement.repository.AssetRepository;
import com.freelancer.assetmanagement.repository.ITAssetRepository;

@Service
@Transactional
public class ITAssetServiceImp implements ITAssetService {

	private static final boolean ACTIVE = true;

	Logger log = LoggerFactory.getLogger(ITAssetServiceImp.class);
	@Autowired
	protected ITAssetRepository iTAssetRepository;

	@Autowired
	protected AssetRepository assetRepository;

	@Override
	public ITAssetDto saveITAsset(ITAssetDto iTAssetDto) {
		ITAsset iTAsset = new ITAsset();
		iTAssetDto.setActive(ACTIVE);
		BeanUtils.copyProperties(iTAssetDto, iTAsset);
		ITAsset save = iTAssetRepository.save(iTAsset);

		iTAssetDto.setId(save.getId());
		iTAssetDto.setCreatedAt(save.getCreatedAt());
		iTAssetDto.setCreatedBy(save.getCreatedBy());
		iTAssetDto.setModifiedAt(save.getModifiedAt());
		iTAssetDto.setModifiedBy(save.getModifiedBy());
		return iTAssetDto;
	}

	@Override
	public ITAssetDto updateITAsset(ITAssetDto iTAssetDto) {
		ITAsset findByAssetId = iTAssetRepository.findByIdAndActive(iTAssetDto.getId(), ACTIVE);
		if (findByAssetId != null) {
			iTAssetDto.setId(findByAssetId.getId());
			iTAssetDto.setCreatedAt(findByAssetId.getCreatedAt());
			iTAssetDto.setCreatedBy(findByAssetId.getCreatedBy());
			BeanUtils.copyProperties(iTAssetDto, findByAssetId);

			ITAsset updatedAsset = iTAssetRepository.save(findByAssetId);

			iTAssetDto.setModifiedAt(updatedAsset.getModifiedAt());
			iTAssetDto.setModifiedBy(updatedAsset.getModifiedBy());
			return iTAssetDto;
		} else {
			log.info("IT Asset not found so update is not possible");
			return null;
		}
	}

	@Override
	public List<ITAssetDto> fetchAllITAssets() {
		List<ITAssetDto> assetDtos = new ArrayList<ITAssetDto>();

//		List<Employee> employees = assetRepository.findAll();
		List<ITAsset> assets = iTAssetRepository.findByActive(ACTIVE);
		for (ITAsset asset : assets) {
			ITAssetDto assetDto = new ITAssetDto();
			BeanUtils.copyProperties(asset, assetDto);
			assetDtos.add(assetDto);

		}
		return assetDtos;
	}

	@Override
	public ITAssetDto findITAssetById(long id) {
		ITAssetDto assetDto = new ITAssetDto();
		ITAsset iTAsset = iTAssetRepository.findByIdAndActive(id, ACTIVE);
		BeanUtils.copyProperties(iTAsset, assetDto);
		return assetDto;
	}

	@Override
	public String deleteITAssetById(long id) {
		ITAsset findByAssetId = iTAssetRepository.findByIdAndActive(id, ACTIVE);
		if (findByAssetId != null) {
//			employeeRepository.delete(findByEmployeeId);
			findByAssetId.setActive(!ACTIVE);
			iTAssetRepository.save(findByAssetId);
			return "deleted Successfully";
		} else {
			log.info("IT Asset not found so delete is not possible");
			return "IT Asset not found to delete";
		}
	}

	@Override
	public String deleteITAssetByAssetId(long assetId) {
		Asset findByAssetIdAndActive = assetRepository.findByAssetIdAndActive(assetId, ACTIVE);
		ITAsset findByAssetId = iTAssetRepository.findByAssetAndActive(findByAssetIdAndActive, ACTIVE);
		log.info("logging-> {}", findByAssetId);
		if (findByAssetId != null) {
//			employeeRepository.delete(findByEmployeeId);
			findByAssetId.setActive(!ACTIVE);
			iTAssetRepository.save(findByAssetId);
			return "deleted Successfully";
		} else {
			log.info("IT Asset not found so delete is not possible");
			return "IT Asset not found to delete";
		}
	}

	@Override
	public double totalITAssetCost() {

		double cost = 0;
		log.info("Inside TotalITAssetcost method in ITAssetImp class");
		List<ITAsset> findByActive = iTAssetRepository.findByActive(ACTIVE);
		if (Objects.nonNull(findByActive)) {
			for (ITAsset iTAsset : findByActive) {
				if (Objects.nonNull(iTAsset.getAsset())) {
					cost += iTAsset.getAsset().getCost();
				}
			}
			return cost;
		}
		return 0;
	}

	@Override
	public ITAssetDto findITAssetByAssetId(long assetId) {

		Asset findByAssetIdAndActive = assetRepository.findByAssetIdAndActive(assetId, ACTIVE);
		ITAsset findByAssetId = iTAssetRepository.findByAssetAndActive(findByAssetIdAndActive, ACTIVE);
		log.info("logging-> {}", findByAssetId);
		if (Objects.nonNull(findByAssetId)) {
			log.info("findITAssetByAssetId is present" );
			ITAssetDto itAssetDto=new ITAssetDto();
			BeanUtils.copyProperties(findByAssetId, itAssetDto);
			return itAssetDto;
		}
		log.info("findITAssetByAssetId is not present" );
		return null;
	}

}
