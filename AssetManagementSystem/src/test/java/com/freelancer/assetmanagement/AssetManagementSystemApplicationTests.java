//package com.freelancer.assetmanagement;
//
//import static org.junit.jupiter.api.Assertions.assertInstanceOf;
//
//import java.util.List;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.freelancer.assetmanagement.dto.EmployeeData;
//import com.freelancer.assetmanagement.dto.EmployeeDto;
//import com.freelancer.assetmanagement.model.Employee;
//import com.freelancer.assetmanagement.repository.EmployeeRepository;
//import com.freelancer.assetmanagement.service.EmployeeService;
//
//@SpringBootTest
//class AssetManagementSystemApplicationTests {
//	
//	public static final Boolean active=true;
//
//	@Test
//	void contextLoads() {
//	}
//	
//	private static String employeeId;
//	private static EmployeeDto employeeDto;
//	private static EmployeeData employeeData;
//
//	@Autowired
//	private EmployeeService employeeService;
//	
//	@Autowired
//	private EmployeeRepository employeeRepository;
//	
//	@BeforeAll
//	public static void startUp() {
//		
//		System.out.println("before test class");
//	}
//	
//	@BeforeEach
//	public void setUp() {
//		employeeId="emp1234543";
//		employeeData=new EmployeeData();
//		employeeDto=new EmployeeDto();
//		Employee employee=employeeRepository.findByEmployeeIdAndActive(employeeId,active);
//		BeanUtils.copyProperties(employee,employeeData);
//		BeanUtils.copyProperties(employee,employeeDto);
//		employeeData.setPassword("k1234");
//		System.out.println("employeeDto->"+employeeDto);
//		
//		System.out.println("before each test method");
//	}
//	@Test
//	public void testSaveEmployee() {
//		System.out.println(employeeDto);
//		assertInstanceOf(EmployeeData.class, employeeService.saveEmployee(employeeData));
//	}
//	@Test
//	public void testFindEmployeeById() {
//		assertInstanceOf(EmployeeDto.class, employeeService.findEmployeeById(employeeId));
//	}
//	
//	@Test
//	public void testUpdateEmployeeByEmployeeId() {
//		assertInstanceOf(EmployeeData.class, employeeService.updateEmployeeByEmployeeId(employeeData));
//	}
//	
//	@Test
//	public void testDeleteEmployeeByEmployeeId() {
//		assertInstanceOf(String.class, employeeService.deleteEmployeeByEmployeeId("emp1234547"));
//	}
//	
//	@Test
//	public void testFetchAllEmployees() {
//		assertInstanceOf(List.class, employeeService.fetchAllEmployees());
//	}
//	
//	@AfterEach
//	public void tearDown() {
//		System.out.println("Runs after each test cases");
//	}
//	
//	@AfterAll
//	public static void endTest() {
//		System.out.println("End of all test cases");
//	}
//
//}
