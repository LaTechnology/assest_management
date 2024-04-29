package com.freelancer.assetmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancer.assetmanagement.model.Asset;
import com.freelancer.assetmanagement.model.Employee;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long>{

	public Asset findByAssetIdAndActive(Long assetId, boolean active);

	public List<Asset> findByActive(boolean active);

	public Optional<List<Asset>> findByEmployeeAndActive(Employee employee, boolean active);

	
}
