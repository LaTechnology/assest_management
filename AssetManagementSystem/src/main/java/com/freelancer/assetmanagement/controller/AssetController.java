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

import com.freelancer.assetmanagement.dto.AssetCount;
import com.freelancer.assetmanagement.dto.AssetDto;
import com.freelancer.assetmanagement.dto.AssetWithFixedAssetDto;
import com.freelancer.assetmanagement.dto.AssetWithITAssetDto;
import com.freelancer.assetmanagement.service.AssetService;
import com.freelancer.assetmanagement.service.FixedAssetService;
import com.freelancer.assetmanagement.service.ITAssetService;

import com.freelancer.assetmanagement.util.ResponseStructure;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/asset")
@CrossOrigin(origins ="http://localhost:4200/",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = {"Content-type","Authorization"},allowCredentials = "true")


@Tag(
        name = "Asset Controller",
        description = "Asset related api's are return here"
)

public class AssetController {
	
	Logger log=LoggerFactory.getLogger(AssetController.class);
	@Autowired
	protected AssetService assetService;
	
	@Autowired
	protected FixedAssetService fixedAssetService;
	
	@Autowired
	protected ITAssetService iTAssetService;
	

	@PostMapping("/saveAsset")
	public ResponseEntity<ResponseStructure<AssetDto>> saveAsset(@RequestBody AssetDto assetDto) {
		log.info("AssetDto-> {}",assetDto);
		
		ResponseStructure<AssetDto> response = new ResponseStructure<>();

		
		AssetDto savedAsset = assetService.saveAsset(assetDto);

		log.info("Logging savedAsset-> {}",savedAsset);
		if (Objects.nonNull(savedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Asset saved successfully");
			response.setData(savedAsset);
//			ResponseEntity<ResponseStructure<AssetDto>> responseEntity=new ResponseEntity<>(response,HttpStatus.OK);
//			return responseEntity;
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Asset not saved");
			response.setData(null);
//			ResponseEntity<ResponseStructure<AssetDto>> responseEntity=new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
//			return responseEntity;
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	

	@PostMapping("/saveAssetWithFixed")
	public ResponseEntity<ResponseStructure<AssetWithFixedAssetDto>> saveAssetAndFixedAsset(@RequestBody AssetWithFixedAssetDto assetWithFixedAssetDto) {
		log.info("AssetWithFixedAssetDto-> {}",assetWithFixedAssetDto);
		ResponseStructure<AssetWithFixedAssetDto> response = new ResponseStructure<>();

		
		AssetWithFixedAssetDto savedAsset = assetService.saveAssetWithFixedAsset(assetWithFixedAssetDto);

		log.info("Logging-> {}",savedAsset);
		if (Objects.nonNull(savedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Asset with FixedAsset saved successfully");
			response.setData(savedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);

		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Asset with FixedAsset not saved");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

		}
	}
	
	@PostMapping("/saveAssetWithIT")
	public ResponseEntity<ResponseStructure<AssetWithITAssetDto>> saveAssetAndITAsset(@RequestBody AssetWithITAssetDto assetWithITAssetDto) {
		log.info("AssetWithITAssetDto-> {}",assetWithITAssetDto);
		ResponseStructure<AssetWithITAssetDto> response = new ResponseStructure<>();

		
		AssetWithITAssetDto savedAsset = assetService.saveAssetWithITAsset(assetWithITAssetDto);

		log.info("Logging-> {}",savedAsset);
		if (Objects.nonNull(savedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Asset with ITAsset saved successfully");
			response.setData(savedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Asset with ITAsset not saved");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

		}
	}

	@PutMapping("/updateAsset")
	public ResponseEntity<ResponseStructure<AssetDto>> updateAsset(@RequestBody AssetDto assetDto) {
		log.info("AssetDto-> {}",assetDto);
		ResponseStructure<AssetDto> response = new ResponseStructure<>();

		
		AssetDto updateAsset = assetService.updateAsset(assetDto);

		log.info("Logging-> {}",updateAsset);
		if (Objects.nonNull(updateAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Asset update successfully");
			response.setData(updateAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Asset not updated");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@GetMapping("/getAllAssets")
	public ResponseEntity<ResponseStructure<List<AssetDto>>> getAllAssets(){
		ResponseStructure<List<AssetDto>> response = new ResponseStructure<>();
		List<AssetDto> fetchedAssets = assetService.fetchAllAssets();

		log.info("Logging fetchedAssets-> {}",fetchedAssets);
		if (Objects.nonNull(fetchedAssets)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All Assets fetched successfully");
			response.setData(fetchedAssets);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Assets not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAssetById")

	public ResponseEntity<ResponseStructure<AssetDto>> getAssetById(@RequestParam Long assetId) {

		ResponseStructure<AssetDto> response = new ResponseStructure<>();
		AssetDto fetchedAsset = assetService.findAssetByAssetId(assetId);

		log.info("Logging fetchedAsset-> {}",fetchedAsset);
		if (Objects.nonNull(fetchedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Asset fetched successfully");
			response.setData(fetchedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Asset not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteAsset")
	public ResponseEntity<ResponseStructure<String>> deleteEmployeeByAssetId(@RequestParam long assetId) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = assetService.deleteAssetByAssetId(assetId);

		log.info("Logging delete Message-> {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("AssetDto deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("AssetDto not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteAssetByEmployeeId")
	public ResponseEntity<ResponseStructure<String>> deleteEmployeeByEmployeeId(@RequestParam String employeeId) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = assetService.deleteAssetByEmployeeId(employeeId);

		log.info("Logging delete Message-> {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("AssetDto deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("AssetDto not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getCount")
	public AssetCount getCount() {

		log.info("Inside getCount method in AssetController Class");
		List<AssetDto> fetchedAssets = assetService.fetchAllAssets();
		log.info("fetchedAssets-> {}",fetchedAssets);
		List<AssetDto> fetchTotalAssets=assetService.fetchTotalAssets();
		log.info("fetchTotalAssets-> {}",fetchTotalAssets);
		AssetCount count=new AssetCount();
		count.setActiveCount(fetchedAssets.size());
		log.info("count.getActiveCount-> {}",count.getActiveCount());
		count.setTotalCount(fetchTotalAssets.size());
		log.info("count.getTotalCount()-> {}" ,count.getTotalCount());
		count.setInactiveCount(fetchTotalAssets.size()-fetchedAssets.size());
		log.info("count.getInactiveCount()-> {}",count.getInactiveCount());
		count.setFixedAssetCost(fixedAssetService.totalFixedAssetCost());
		log.info("count.getFixedAssetCost()-> {}",count.getFixedAssetCost());
		count.setITAssetCost(iTAssetService.totalITAssetCost());
		log.info("count.getITAssetCost()-> {}",count.getITAssetCost());
		return count;
	}
	
	@GetMapping("/getAssetByEmployeeId")
	public ResponseEntity<ResponseStructure<List<AssetDto>>> getAssetByEmployeeId(@RequestParam String employeeId) {
		ResponseStructure<List<AssetDto>> response = new ResponseStructure<>();
		List<AssetDto> fetchedAsset = assetService.findAssetByEmployeeId(employeeId);

		log.info("Logging fetchedAsset-> {}",fetchedAsset);
		if (Objects.nonNull(fetchedAsset)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Asset fetched successfully");
			response.setData(fetchedAsset);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Asset not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}


}
