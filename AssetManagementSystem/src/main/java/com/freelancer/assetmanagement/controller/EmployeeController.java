package com.freelancer.assetmanagement.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import com.freelancer.assetmanagement.dto.EmployeeData;
import com.freelancer.assetmanagement.dto.EmployeeDto;
import com.freelancer.assetmanagement.model.AuthRequest;
import com.freelancer.assetmanagement.model.GeneratedToken;
import com.freelancer.assetmanagement.service.EmployeeService;
import com.freelancer.assetmanagement.service.JwtService;
import com.freelancer.assetmanagement.util.ResponseStructure;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/employee")
@CrossOrigin(originPatterns ="*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = {"Content-type","Authorization"})


@Tag(
        name = "Employee Controller",
        description = "Employee related api's are return here"
)
public class EmployeeController {

	Logger log=LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	protected EmployeeService employeeService;
	
	@Autowired
	protected JwtService jwtService;
	
	@Autowired
	protected AuthenticationManager authenticationManager;
	
	@Autowired
	protected GeneratedToken generatedToken;

//	 @Operation(
//	            summary = "",
//	            description = ""
//	    )
//	    @ApiResponses({
//	            @ApiResponse(
//	                    responseCode = "201",
//	                    description = "HTTP Status CREATED"
//	            ),
//	            @ApiResponse(
//	                    responseCode = "500",
//	                    description = "HTTP Status Internal Server Error",
//	                    content = @Content(
//	                            schema = @Schema(implementation = ErrorResponseDto.class)
//	                    )
//	            )
//	    }
//	    ) 
	@PostMapping("/postEmployee")
	public ResponseEntity<ResponseStructure<EmployeeData>> saveEmployee(@RequestBody EmployeeData employeeData) {
		log.info("EmployeeData-> {}",employeeData);
		ResponseStructure<EmployeeData> response = new ResponseStructure<>();
//		EmployeeData data=EmployeeData.builder().build();
//		data.setEmployeeId(employeeData.getEmployeeId());
		
		EmployeeData savedEmployee = employeeService.saveEmployee(employeeData);

		log.info("Logging-> {}",savedEmployee);
		if (Objects.nonNull(savedEmployee)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Employee saved successfully");
			response.setData(savedEmployee);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Employee not saved");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<ResponseStructure<List<EmployeeDto>>> getAllEmployees(){
		ResponseStructure<List<EmployeeDto>> response = new ResponseStructure<>();
		List<EmployeeDto> fetchedEmployees = employeeService.fetchAllEmployees();

		log.info("Logging fetchedEmployees-> {}",fetchedEmployees);
		if (Objects.nonNull(fetchedEmployees)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All Employees fetched successfully");
			response.setData(fetchedEmployees);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Employees not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/getEmployeeById")
	public ResponseEntity<ResponseStructure<EmployeeDto>> getEmployeeById(@RequestParam String employeeId) {
		ResponseStructure<EmployeeDto> response = new ResponseStructure<>();
		EmployeeDto fetchedEmployee = employeeService.findEmployeeById(employeeId);

		log.info("Logging fetchedEmployee-> {}",fetchedEmployee);
		if (Objects.nonNull(fetchedEmployee)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Employee fetched successfully");
			response.setData(fetchedEmployee);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Employee not fetched");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<ResponseStructure<EmployeeData>> updateEmployeeByEmployeeId(@RequestBody EmployeeData employeeData) {
		ResponseStructure<EmployeeData> response = new ResponseStructure<>();
		EmployeeData updateEmployee = employeeService.updateEmployeeByEmployeeId(employeeData);

		log.info("Logging updateEmployee-> {}",updateEmployee);
		if (Objects.nonNull(updateEmployee)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Employee updated successfully");
			response.setData(updateEmployee);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Employee not updated");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<ResponseStructure<String>> deleteEmployeeByEmployeeId(@RequestParam String employeeId) {
		ResponseStructure<String> response = new ResponseStructure<>();
		String deleteMessage = employeeService.deleteEmployeeByEmployeeId(employeeId);

		log.info("Logging delete Message-> {}",deleteMessage);
		if (Objects.nonNull(deleteMessage)) {
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Employee deleted successfully");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Employee not deleted");
			response.setData(deleteMessage);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<ResponseStructure<GeneratedToken>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		log.info("authenticate email Id -> {} password-> {}",authRequest.getEmailId(),authRequest.getPassword());
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));
		log.info("authenticate -> {}",authenticate);
		ResponseStructure<GeneratedToken> response=new ResponseStructure<>();
		if(authenticate.isAuthenticated()) {
			
			generatedToken.setToken(jwtService.generateToken(authRequest.getEmailId()));
			generatedToken.setEmployeeDto(employeeService.findEmployeeByEmailId(authRequest.getEmailId()));
//			generatedToken.setOrganizationId(authRequest.getOrganizationId()); //org is setted in employeeServiceImp class
//			log.info("orgnaization id in generated token->"+generatedToken.getOrganizationDto());

			response.setStatus(HttpStatus.OK.value());
			response.setData(generatedToken);
			response.setMessage("Token Generated Successfully");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			throw new UsernameNotFoundException("Invalid User Name");
		}
	}
	
	@DeleteMapping("/deleteByEmployeeId")
	public Integer deleteByEmployeeId(@RequestParam String employeeId) {
		return employeeService.deleteByEmployeeId(employeeId);
		 
	}
}
