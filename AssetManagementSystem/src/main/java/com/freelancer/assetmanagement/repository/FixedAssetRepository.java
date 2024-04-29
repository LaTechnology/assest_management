package com.freelancer.assetmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.FixedAsset;

public interface FixedAssetRepository extends JpaRepository<FixedAsset, Long>{

	public FixedAsset findByIdAndActive(long id, boolean active);

	public List<FixedAsset> findByActive(boolean active);

	public FixedAsset findByAssetAndActive(Asset asset, boolean active);

}
