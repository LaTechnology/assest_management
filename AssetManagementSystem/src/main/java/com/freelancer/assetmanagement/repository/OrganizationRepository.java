package com.freelancer.assetmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancer.assetmanagement.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{

	public Optional<Organization> findByOrganizationId(long organizationId);

	public List<Organization> findByActive(Boolean active);

	public Optional<Organization> findByOrganizationIdAndActive(long organizationId, Boolean active);

}
