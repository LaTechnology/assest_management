package com.freelancer.assetmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelancer.assetmanagement.config.UserInfoUserDetails;
import com.freelancer.assetmanagement.controller.EmployeeController;
import com.freelancer.assetmanagement.dto.EmployeeData;
import com.freelancer.assetmanagement.dto.EmployeeDto;

import com.freelancer.assetmanagement.dto.OrganizationDto;
import com.freelancer.assetmanagement.model.AuthRequest;
import com.freelancer.assetmanagement.model.Employee;
import com.freelancer.assetmanagement.model.GeneratedToken;
import com.freelancer.assetmanagement.repository.EmployeeRepository;
import com.freelancer.assetmanagement.repository.OrganizationRepository;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService, UserDetailsService {

	public static final Boolean ACTIVE = true;
	Logger log = LoggerFactory.getLogger(EmployeeServiceImp.class);

	@Autowired
	protected EmployeeRepository employeeRepository;

	@Autowired
	protected OrganizationRepository organizationRepository;

	@Autowired
	protected PasswordEncoder encoder;

	@Autowired
	protected AuthRequest authRequest;

	@Autowired
	protected GeneratedToken generatedToken;

	@Override
	public EmployeeData saveEmployee(EmployeeData employeeData) {
		log.info("Inside SaveEmployee of EmployeeServiceImp class");
		employeeData.setPassword(encoder.encode(employeeData.getPassword()));
//		if(employee.getOrganization()!=null) {
//			organizationRepository.save(employee.getOrganization());
//		}

		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeData, employee);
		employee.setActive(ACTIVE);

		Employee savedEmployee = employeeRepository.save(employee);
		if (Objects.nonNull(savedEmployee)) {
			employeeData.setId(savedEmployee.getId());
			employeeData.setCreatedAt(savedEmployee.getCreatedAt());
			employeeData.setCreatedBy(savedEmployee.getCreatedBy());
			employeeData.setModifiedAt(savedEmployee.getModifiedAt());
			employeeData.setModifiedBy(savedEmployee.getModifiedBy());

			return employeeData;
		} else {
			return null;
		}
	}

	@Override
	public List<EmployeeDto> fetchAllEmployees() {
		log.info("Inside fetchAllEmployees of EmployeeServiceImp class");

		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();

//		List<Employee> employees = employeeRepository.findAll();
		List<Employee> employees = employeeRepository.findByActive(ACTIVE);
		for (Employee employee : employees) {
			EmployeeDto employeeDto = new EmployeeDto();
			BeanUtils.copyProperties(employee, employeeDto);
			employeeDtos.add(employeeDto);

		}
		return employeeDtos;
	}

	@Override
	public EmployeeDto findEmployeeById(String employeeId) {
		log.info("Inside fetchAllEmployeeById of EmployeeServiceImp class");
		EmployeeDto employeeDto = new EmployeeDto();
		Employee employee = employeeRepository.findByEmployeeIdAndActive(employeeId, ACTIVE);
		if(Objects.nonNull(employee)) {
			BeanUtils.copyProperties(employee, employeeDto);
			return employeeDto;
		}
		return null;
		
	}

	@Override
	public EmployeeData updateEmployeeByEmployeeId(EmployeeData employeeData) {
		log.info("Inside updateEmployeeByEmployeeId of EmployeeServiceImp class");

		Employee findByEmployeeId = employeeRepository.findByEmployeeIdAndActive(employeeData.getEmployeeId(), ACTIVE);
		if (findByEmployeeId != null) {
//			employeeData.setPassword(encoder.encode(employeeData.getPassword()));
			employeeData.setId(findByEmployeeId.getId());
			if( Objects.isNull(employeeData.getActive()) || employeeData.getActive()) {
				employeeData.setActive(ACTIVE);
			}
			else {
				employeeData.setActive(!ACTIVE);
			}
			if(Objects.isNull(employeeData.getRole())) {
			employeeData.setRole(findByEmployeeId.getRole());
			}
			if(Objects.isNull(employeeData.getEmployeeImage())) {
				employeeData.setEmployeeImage(findByEmployeeId.getEmployeeImage());
			}
			employeeData.setEmployeeId(findByEmployeeId.getEmployeeId());
			employeeData.setCreatedAt(findByEmployeeId.getCreatedAt());
			employeeData.setCreatedBy(findByEmployeeId.getCreatedBy());

			BeanUtils.copyProperties(employeeData, findByEmployeeId);

			Employee updatedEmployee = employeeRepository.save(findByEmployeeId);
			employeeData.setCreatedAt(updatedEmployee.getCreatedAt());
			employeeData.setCreatedBy(updatedEmployee.getCreatedBy());
			employeeData.setModifiedAt(updatedEmployee.getModifiedAt());
			employeeData.setModifiedBy(updatedEmployee.getModifiedBy());
			return employeeData;
		} else {
			log.info("Employee not found so update is not possible");
			return null;
		}
	}

	@Override
	public String deleteEmployeeByEmployeeId(String employeeId) {
		log.info("Inside deleteEmployeeByEmployeeId of EmployeeServiceImp class");

		Employee findByEmployeeId = employeeRepository.findByEmployeeIdAndActive(employeeId, ACTIVE);
		if (findByEmployeeId != null) {
//			employeeRepository.delete(findByEmployeeId);
			findByEmployeeId.setActive(!ACTIVE);
			employeeRepository.save(findByEmployeeId);
			return "deleted Successfully";
		} else {
			log.info("Employee not found so delete is not possible");
			return "Employee not found to delete";
		}
	}

	public Integer deleteByEmployeeId(String employeeId) {
		log.info("Inside deleteByEmployeeId  of EmployeeServiceImp class");
		Employee findByEmployeeId = employeeRepository.findByEmployeeIdAndActive(employeeId, ACTIVE);

		if (findByEmployeeId != null) {
//			Optional<Integer> deleteByEmployeeId = employeeRepository.deleteByEmployeeIdAndActive(employeeId,ACTIVE);
//			if(deleteByEmployeeId.isPresent()) {
//			return deleteByEmployeeId.get();
//			}
//			else {
//				return Integer.valueOf(0);
//			}
			findByEmployeeId.setActive(!ACTIVE);
			employeeRepository.save(findByEmployeeId);
			return 1;
		} else {
			log.info("Employee not found so delete is not possible");
			return Integer.valueOf(-1);
		}

	}

	public EmployeeDto findEmployeeByEmailId(String emailId) {
		log.info("Inside findEmployeeByEmailId of EmployeeServiceImp class");
		EmployeeDto employeeDto = new EmployeeDto();
		Optional<Employee> findByEmailIdAndActive = employeeRepository.findByEmailIdAndActive(emailId, ACTIVE);
		if (findByEmailIdAndActive.isPresent()) {
			BeanUtils.copyProperties(findByEmailIdAndActive.get(), employeeDto);
			return employeeDto;
		} else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		log.info("Inside loadUserByUsername of EmployeeServiceImp class");
//		String firstName = username.split(" ")[0];
//		String lastName = username.split(" ")[1];
//		System.out.println("firstName=" + firstName + ", lastName=" + lastName);

		Optional<Employee> userInfo = employeeRepository.findByEmailIdAndActive(emailId, ACTIVE);
		
		if (userInfo.isPresent()) {
			log.info("Employee-> {}", userInfo.get());
			OrganizationDto dto = new OrganizationDto();
			if (Objects.nonNull(userInfo.get().getOrganization())) {
				BeanUtils.copyProperties(userInfo.get().getOrganization(), dto);
				authRequest.setOrganizationDto(dto);
			}
//			generatedToken.setOrganizationDto(authRequest.getOrganizationDto());
			log.info("orgnaization id in EmployeeServiceImp-> {}", authRequest.getOrganizationDto());

		}
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found " + emailId));
	}

}
