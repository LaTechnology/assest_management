package com.freelancer.assetmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.ITAsset;

@Repository
public interface ITAssetRepository extends JpaRepository<ITAsset, Long>{

	public ITAsset findByIdAndActive(long id, boolean active);

	public List<ITAsset> findByActive(boolean active);

	public ITAsset findByAssetAndActive(Asset asset, boolean active);

//	public Optional<ITAsset> findByAssetIdAndActive(long assetId,boolean active);
	
}
