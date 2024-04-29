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

import com.freelancer.assetmanagement.dto.ITAssetDto;
import com.freelancer.assetmanagement.service.ITAssetService;
import com.freelancer.assetmanagement.util.ResponseStructure;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ITAsset")
@CrossOrigin(origins ="http://localhost:4200/",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = {"Content-type","Authorization"},allowCredentials = "true")


@Tag(
        name = "ITAsset Controller",
        description = "IT Asset related api's are return here"
)
public class ITAssetController {

	Logger log=LoggerFactory.getLogger(ITAssetController.class);
	
	@Autowired
	protected ITAssetService iTAssetService;
	
	@PostMapping("/saveITAsset")
	public ResponseEntity<ResponseStructure<ITAssetDto>> saveITAsset(@RequestBody ITAssetDto iTAssetDto) {
		log.info("iTAssetDto-> {}",iTAssetDto);
		ResponseStructure<ITAssetDto> response = new ResponseStructure<>();

		
		ITAssetDto savedITAsset =iTAssetService.saveITAsset(iTAssetDto);

		log.info("Logging-> {}",savedITAsset);
		if (Objects.nonNull(savedITAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("IT Asset saved successfully");
			response.setData(savedITAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("IT Asset not saved");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateITAsset")
	public ResponseEntity<ResponseStructure<ITAssetDto>> updateITAsset(@RequestBody ITAssetDto iTAssetDto) {
		log.info("FixedAssetDto-> {}",iTAssetDto);
		ResponseStructure<ITAssetDto> response = new ResponseStructure<>();

		
		ITAssetDto updateITAsset = iTAssetService.updateITAsset(iTAssetDto);

		log.info("Logging-> {}",updateITAsset);
		if (Objects.nonNull(updateITAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("IT Asset update successfully");
			response.setData(updateITAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("IT Asset not updated");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/getAllITAssets")
	public ResponseEntity<ResponseStructure<List<ITAssetDto>>> getAllITAssets(){
		ResponseStructure<List<ITAssetDto>> response = new ResponseStructure<>();
		List<ITAssetDto> fetchedAssets = iTAssetService.fetchAllITAssets();

		log.info("Logging fetchedAssets-> {}",fetchedAssets);
		if (Objects.nonNull(fetchedAssets)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All IT Assets fetched successfully");
			response.setData(fetchedAssets);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("IT Assets not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getITAssetById")
	public ResponseEntity<ResponseStructure<ITAssetDto>> getFixedAssetById(@RequestParam long id) {
		ResponseStructure<ITAssetDto> response = new ResponseStructure<>();
		ITAssetDto fetchedAsset = iTAssetService.findITAssetById(id);

		log.info("Logging fetchedAsset-> {}",fetchedAsset);
		if (Objects.nonNull(fetchedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("IT Asset fetched successfully");
			response.setData(fetchedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("IT Asset not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteITAsset")
	public ResponseEntity<ResponseStructure<String>> deleteAssetById(@RequestParam long id) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = iTAssetService.deleteITAssetById(id);

		log.info("Logging delete Message-> {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("IT Asset deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("IT Asset not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteITAssetByAssetId")
	public ResponseEntity<ResponseStructure<String>> deleteITAssetByAssetId(@RequestParam long assetId) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = iTAssetService.deleteITAssetByAssetId(assetId);

		log.info("Logging delete Message-> {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("IT Asset deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("IT Asset not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getITAssetByAssetId")
	public ResponseEntity<ResponseStructure<ITAssetDto>> getFixedAssetByAssetId(@RequestParam long assetId) {
		ResponseStructure<ITAssetDto> response = new ResponseStructure<>();
		ITAssetDto fetchedAsset = iTAssetService.findITAssetByAssetId(assetId);

		log.info("Logging fetchedAsset-> {}",fetchedAsset);
		if (Objects.nonNull(fetchedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("IT Asset fetched successfully");
			response.setData(fetchedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("IT Asset not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	} 
}
