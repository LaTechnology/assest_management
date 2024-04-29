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

import com.freelancer.assetmanagement.dto.FixedAssetDto;
import com.freelancer.assetmanagement.service.FixedAssetService;
import com.freelancer.assetmanagement.util.ResponseStructure;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/fixedAsset")
@CrossOrigin(origins ="http://localhost:4200/",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = {"Content-type","Authorization"},allowCredentials = "true")


@Tag(
        name = "FixedAsset Controller",
        description = "Fixed Asset related api's are return here"
)
public class FixedAssetController {

	Logger log=LoggerFactory.getLogger(FixedAssetController.class);
	
	@Autowired
	protected FixedAssetService fixedAssetService;
	
	@PostMapping("/saveFixedAsset")
	public ResponseEntity<ResponseStructure<FixedAssetDto>> saveFixedAsset(@RequestBody FixedAssetDto fixedAssetDto) {
		log.info("fixedAssetDto-> {}",fixedAssetDto);
		ResponseStructure<FixedAssetDto> response = new ResponseStructure<>();

		
		FixedAssetDto savedFixedAsset = fixedAssetService.saveFixedAsset(fixedAssetDto);

		log.info("Logging-> {}",savedFixedAsset);
		if (Objects.nonNull(savedFixedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Asset saved successfully");
			response.setData(savedFixedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Asset not saved");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateFixedAsset")
	public ResponseEntity<ResponseStructure<FixedAssetDto>> updateFixedAsset(@RequestBody FixedAssetDto fixedAssetDto) {
		log.info("FixedAssetDto-> {}",fixedAssetDto);
		ResponseStructure<FixedAssetDto> response = new ResponseStructure<>();

		
		FixedAssetDto updateFixedAsset = fixedAssetService.updateFixedAsset(fixedAssetDto);

		log.info("Logging-> {}",updateFixedAsset);
		if (Objects.nonNull(updateFixedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Fixed Asset update successfully");
			response.setData(updateFixedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Fixed Asset not updated");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/getAllFixedAssets")
	public ResponseEntity<ResponseStructure<List<FixedAssetDto>>> getAllFixedAssets(){
		ResponseStructure<List<FixedAssetDto>> response = new ResponseStructure<>();
		List<FixedAssetDto> fetchedAssets = fixedAssetService.fetchAllFixedAssets();

		log.info("Logging fetchedAssets-> {}",fetchedAssets);
		if (Objects.nonNull(fetchedAssets)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All fixed Assets fetched successfully");
			response.setData(fetchedAssets);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Fixed Assets not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getFixedAssetById")
	public ResponseEntity<ResponseStructure<FixedAssetDto>> getFixedAssetById(@RequestParam long id) {
		ResponseStructure<FixedAssetDto> response = new ResponseStructure<>();
		FixedAssetDto fetchedAsset = fixedAssetService.findFixedAssetById(id);

		log.info("Logging fetchedAsset-> {}",fetchedAsset);
		if (Objects.nonNull(fetchedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Fixed Asset fetched successfully");
			response.setData(fetchedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Fixed Asset not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteFixedAsset")
	public ResponseEntity<ResponseStructure<String>> deleteAssetByAssetId(@RequestParam long id) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = fixedAssetService.deleteFixedAssetById(id);

		log.info("Logging delete Message-> {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Fixed Asset deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Fixed Asset not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteFixedAssetByAssetId")
	public ResponseEntity<ResponseStructure<String>> deleteFixedAssetByAssetId(@RequestParam long assetId) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = fixedAssetService.deleteFixedAssetByAssetId(assetId);

		log.info("Logging delete Message-> {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Fixed Asset deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Fixed Asset not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getFixedAssetByAssetId")
	public ResponseEntity<ResponseStructure<FixedAssetDto>> getFixedAssetByAssetId(@RequestParam long assetId) {
		ResponseStructure<FixedAssetDto> response = new ResponseStructure<>();
		FixedAssetDto fetchedAsset = fixedAssetService.findFixedAssetByAssetId(assetId);

		log.info("Logging fetchedAsset-> {}",fetchedAsset);
		if (Objects.nonNull(fetchedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Fixed Asset fetched successfully");
			response.setData(fetchedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Fixed Asset not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
}
