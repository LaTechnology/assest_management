package com.freelancer.assetmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancer.assetmanagement.dto.OrganizationData;
import com.freelancer.assetmanagement.dto.OrganizationDto;
import com.freelancer.assetmanagement.model.Organization;
import com.freelancer.assetmanagement.repository.OrganizationRepository;

@Service
public class OrganizationServiceImp implements OrganizationService {

	public static final Boolean ACTIVE=true;
	@Autowired
	protected OrganizationRepository organizationRepository;

	@Override
	public OrganizationData saveOrganization(OrganizationData organizationData) {
		Organization organization=new Organization();
		BeanUtils.copyProperties(organizationData,organization);
		organization.setActive(ACTIVE);
		Organization savedOrganization = organizationRepository.save(organization);
		organizationData.setOrganizationId(savedOrganization.getOrganizationId());
		organizationData.setCreatedAt(savedOrganization.getCreatedAt());
		organizationData.setCreatedBy(savedOrganization.getCreatedBy());
		organizationData.setModifiedAt(savedOrganization.getModifiedAt());
		organizationData.setModifiedBy(savedOrganization.getModifiedBy());
		return organizationData;
	}

	@Override
	public List<OrganizationDto> fetchAllOrganizations() {
//		List<Organization> organizations = organizationRepository.findAll();
		List<Organization> organizations = organizationRepository.findByActive(ACTIVE);
		
		List<OrganizationDto> dtos=new ArrayList<>();
		for(Organization organization:organizations) {
			OrganizationDto organizationDto=new OrganizationDto();
			BeanUtils.copyProperties(organization,organizationDto);
			dtos.add(organizationDto);
		}
		return dtos;
	}

	@Override
	public OrganizationDto findOrganizationById(long organizationId) {
//		Organization findByOrganizationId = organizationRepository.findByOrganizationId(organizationId).orElse(null);
		Organization findByOrganizationId = organizationRepository.findByOrganizationIdAndActive(organizationId,ACTIVE).orElse(null);
		OrganizationDto organizationDto=new OrganizationDto();
		BeanUtils.copyProperties(findByOrganizationId,organizationDto);
		return organizationDto;
	}

	@Override
	public OrganizationData updateOrganizationByOrganizationId(OrganizationData organizationData) {
		Organization findByOrganizationId = organizationRepository
				.findByOrganizationIdAndActive(organizationData.getOrganizationId(),ACTIVE).orElse(null);

		if (Objects.nonNull(findByOrganizationId)) {

//			organizationData.setOrganizationId(findByOrganizationId.getOrganizationId());
			BeanUtils.copyProperties(organizationData,findByOrganizationId);
			Organization updatedOrganization = organizationRepository.save(findByOrganizationId);
			organizationData.setCreatedAt(updatedOrganization.getCreatedAt());
			organizationData.setCreatedBy(updatedOrganization.getCreatedBy());
			organizationData.setModifiedAt(updatedOrganization.getModifiedAt());
			organizationData.setModifiedBy(updatedOrganization.getModifiedBy());
			return organizationData;
		} else {
			return null;
		}
	}

	@Override
	public String deleteOrganizationByOrganizationId(long organizationId) {
		Organization findByOrganizationId = organizationRepository.findByOrganizationIdAndActive(organizationId,ACTIVE).orElse(null);

		if (Objects.nonNull(findByOrganizationId)) {
//			organizationRepository.delete(findByOrganizationId);
			findByOrganizationId.setActive(!ACTIVE);
			organizationRepository.save(findByOrganizationId);
			return "Organization Deleted successfully";
		} 
		else {
			return "Organization is not present So deletion is not possible";
		}
	}

}
