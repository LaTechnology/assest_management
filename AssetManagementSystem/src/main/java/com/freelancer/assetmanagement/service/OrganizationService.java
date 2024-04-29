package com.freelancer.assetmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancer.assetmanagement.dto.OrganizationData;
import com.freelancer.assetmanagement.dto.OrganizationDto;

@Service
public interface OrganizationService {

	public OrganizationData saveOrganization(OrganizationData organizationData);

	public List<OrganizationDto> fetchAllOrganizations();

	public OrganizationDto findOrganizationById(long organizationId);

	public OrganizationData updateOrganizationByOrganizationId(OrganizationData organizationData);

	public String deleteOrganizationByOrganizationId(long organizationId);

}
