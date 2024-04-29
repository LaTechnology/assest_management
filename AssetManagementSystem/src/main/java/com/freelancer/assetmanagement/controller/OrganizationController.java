package com.freelancer.assetmanagement.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelancer.assetmanagement.dto.OrganizationData;
import com.freelancer.assetmanagement.dto.OrganizationDto;
import com.freelancer.assetmanagement.model.Organization;
import com.freelancer.assetmanagement.service.OrganizationService;
import com.freelancer.assetmanagement.util.ResponseStructure;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/organization")
@CrossOrigin(origins ="http://localhost:4200/",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = {"Content-type","Authorization"},allowCredentials = "true")

@Tag(
        name = "Organization Controller",
        description = "Organization related api's are return here"
)
public class OrganizationController {

	Logger log=LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	protected OrganizationService organizationService;
	
	@PostMapping("/postOrganization")
	public ResponseEntity<ResponseStructure<OrganizationData>> saveEmployee(@RequestBody OrganizationData organizationData) {
		ResponseStructure<OrganizationData> response = new ResponseStructure<>();
		OrganizationData savedOrganization = organizationService.saveOrganization(organizationData);

		log.info("Logging savedOrganization-> {}",savedOrganization);
		if (Objects.nonNull(savedOrganization)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Organization saved successfully");
			response.setData(savedOrganization);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Organization not saved");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/getAllOrganizations")
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<ResponseStructure<List<OrganizationDto>>> getAllOrganizations(){
		ResponseStructure<List<OrganizationDto>> response = new ResponseStructure<>();
		List<OrganizationDto> fetchedOrganizations = organizationService.fetchAllOrganizations();

		log.info("Logging fetchedOrganizations->  {}",fetchedOrganizations);
		if (Objects.nonNull(fetchedOrganizations)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All Organizations fetched successfully");
			response.setData(fetchedOrganizations);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Organizations not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/getOrganizationById")
	public ResponseEntity<ResponseStructure<OrganizationDto>> getOrganizationById(@RequestParam long organizationId) {
		ResponseStructure<OrganizationDto> response = new ResponseStructure<>();
		OrganizationDto fetchedOrganization = organizationService.findOrganizationById(organizationId);

		log.info("Logging fetchedOrganization->  {}",fetchedOrganization);
		if (Objects.nonNull(fetchedOrganization)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Organization fetched successfully");
			response.setData(fetchedOrganization);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Organization not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PutMapping("/updateOrganization")
	public ResponseEntity<ResponseStructure<OrganizationData>> updateOrganizationByOrganizationId(@RequestBody OrganizationData organizationData) {
		ResponseStructure<OrganizationData> response = new ResponseStructure<>();
		OrganizationData updateOrganization = organizationService.updateOrganizationByOrganizationId(organizationData);

		log.info("Logging updateOrganization->  {}",updateOrganization);
		if (Objects.nonNull(updateOrganization)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Organization updated successfully");
			response.setData(updateOrganization);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Organization not updated");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@DeleteMapping("/deleteOrganization")
	public ResponseEntity<ResponseStructure<String>> deleteOrganizationByOrganizationId(@RequestParam long organizationId) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = organizationService.deleteOrganizationByOrganizationId(organizationId);

		log.info("Logging delete Message->  {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Organization deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Organization not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			
		}
	}
	
//	@GetMapping("/getAllOrganizationNames")
//	public ResponseEntity<ResponseStructure<List<String>>> getAllOrganizations(){
//		ResponseStructure<List<String>> response = new ResponseStructure<>();
//		List<Organization> fetchedOrganizations = organizationService.fetchAllOrganizations();
//
//		List<String> listOf
//		log.info("Logging fetchedOrganizations->  {}",fetchedOrganizations);
//		if (Objects.nonNull(fetchedOrganizations)) {
//			response.setStatus(HttpStatus.OK.value());
//			response.setMessage("All Organizations fetched successfully");
//			response.setData(fetchedOrganizations);
//			ResponseEntity<ResponseStructure<List<Organization>>> responseEntity=new ResponseEntity<>(response,HttpStatus.OK);
//			return responseEntity;
//		}
//		else {
//			response.setStatus(HttpStatus.BAD_REQUEST.value());
//			response.setMessage("Organizations not fetched");
//			response.setData(null);
//			ResponseEntity<ResponseStructure<List<Organization>>> responseEntity=new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
//			return responseEntity;
//		}
//	}
	
}
