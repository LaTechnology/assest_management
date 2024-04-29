package com.freelancer.assetmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelancer.assetmanagement.dto.EmployeeData;
import com.freelancer.assetmanagement.dto.EmployeeDto;

@Service
public interface EmployeeService {

	public EmployeeData saveEmployee(EmployeeData employeeData);

	public List<EmployeeDto> fetchAllEmployees();

	public EmployeeDto findEmployeeById(String employeeId);

	public EmployeeData updateEmployeeByEmployeeId(EmployeeData employeeData);

	public String deleteEmployeeByEmployeeId(String employeeId);
	
	public Integer deleteByEmployeeId(String employeeId);
	

	public EmployeeDto findEmployeeByEmailId(String emailId);

}
