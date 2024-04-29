//package com.freelancer.assetmanagement;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.BeanUtils;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.freelancer.assetmanagement.dto.EmployeeData;
//import com.freelancer.assetmanagement.dto.EmployeeDto;
//import com.freelancer.assetmanagement.model.Employee;
//import com.freelancer.assetmanagement.repository.EmployeeRepository;
//import com.freelancer.assetmanagement.repository.OrganizationRepository;
//import com.freelancer.assetmanagement.service.EmployeeServiceImp;
//
////@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class EmployeeServiceTest {
//	
////	private static String employeeId;
////	private static Employee employee;
////
////	@Autowired
////	private EmployeeService employeeService;
////	
////	@BeforeAll
////	public static void startUp() {
////		
////		System.out.println("before test class");
////	}
////	
////	@BeforeEach
////	public void setUp() {
////		employeeId="emp1234543";
////		employee=employeeService.findEmployeeById(employeeId);
////		System.out.println("before each test method");
////	}
////	@Test
////	public void testSaveEmployee() {
////		System.out.println(employee);
////		assertInstanceOf(Employee.class, employeeService.saveEmployee(employee));
////	}
////	@Test
////	public void testFindEmployeeById() {
////		assertInstanceOf(Employee.class, employeeService.findEmployeeById(employeeId));
////	}
////	
////	@Test
////	public void testUpdateEmployeeByEmployeeId() {
////		assertInstanceOf(Employee.class, employeeService.updateEmployeeByEmployeeId(employee));
////	}
////	
////	@Test
////	public void testDeleteEmployeeByEmployeeId() {
////		assertInstanceOf(String.class, employeeService.deleteEmployeeByEmployeeId("emp1234547"));
////	}
////	
////	@Test
////	public void testFetchAllEmployees() {
////		assertInstanceOf(List.class, employeeService.fetchAllEmployees());
////	}
////	
////	@AfterEach
////	public void tearDown() {
////		System.out.println("Runs after each test cases");
////	}
////	
////	@AfterAll
////	public static void endTest() {
////		System.out.println("End of all test cases");
////	}
//	
//	
//	public static final Boolean active=true;
//	 @Mock
//	    private EmployeeRepository employeeRepository;
//
//	    @Mock
//	    private OrganizationRepository organizationRepository;
//
////	    @Autowired
//	    @Mock
//	    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();;
//
//	    @InjectMocks
//	    private EmployeeServiceImp employeeService;
//
//	    @Before
//	    public void setUp() {
//	        MockitoAnnotations.initMocks(this);
//	    }
//
//	    @Test
//	    public void testSaveEmployee() {
//	        EmployeeData employeeData = new EmployeeData();
//	        Employee employee =new Employee();
//	        employee.setEmailId("bean@gmail.com");
//	        employee.setFirstName("Bean");
//	        employee.setLastName("B");
//	        employee.setPassword("b1234");
//	        BeanUtils.copyProperties(employee,employeeData);
//	        String password = "password";
//	        System.out.println(password);
////	        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
//	        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//
//	        EmployeeData savedEmployee = employeeService.saveEmployee(employeeData);
//
//	        assertNotNull(savedEmployee);
////	        assertEquals("encodedPassword", savedEmployee.getPassword());
//	        verify(employeeRepository).save(any(Employee.class));
//	        assertEquals(savedEmployee, employeeData);
//	    }
//	    
//	    @Test
//	    public void testFetchAllEmployees() {
//	        // Given
//	        List<Employee> employees = new ArrayList<>();
//	        employees.add(new Employee());
//	        when(employeeRepository.findAll()).thenReturn(employees);
//
//	        // When
//	        List<EmployeeDto> fetchedEmployees = employeeService.fetchAllEmployees();
//
//	        // Then
//	        assertEquals(1, fetchedEmployees.size());
//	    }
//	    @Test
//	    public void testFindEmployeeById() {
//	        // Given
//	        String employeeId = "123";
//	        Employee employee = new Employee();
//	        when(employeeRepository.findByEmployeeIdAndActive(employeeId,active)).thenReturn(employee);
//
//	        // When
//	        EmployeeDto foundEmployee = employeeService.findEmployeeById(employeeId);
//
//	        // Then
//	        assertNotNull(foundEmployee);
//	    }
//	    
//	    @Test
//	    public void testUpdateEmployeeByEmployeeId() {
//	        // Given
//	        Employee employee = new Employee();
//	        EmployeeData employeeData=new EmployeeData();
//	        employee.setEmployeeId("123");
//	        employeeData.setEmployeeId("123");
//	        when(employeeRepository.findByEmployeeIdAndActive("123",active)).thenReturn(employee);
//	        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//
//	        // When
//	        EmployeeData updatedEmployee = employeeService.updateEmployeeByEmployeeId(employeeData);
//
//	        // Then
//	        assertNotNull(updatedEmployee);
//	        assertEquals(employeeData, updatedEmployee);
//	    }
//	    
//	    @Test
//	    public void testDeleteEmployeeByEmployeeId() {
//	        // Given
//	        String employeeId = "123";
//	        Employee employee = new Employee();
//	        when(employeeRepository.findByEmployeeIdAndActive(employeeId,active)).thenReturn(employee);
//
//	        // When
//	        String result = employeeService.deleteEmployeeByEmployeeId(employeeId);
//
//	        // Then
//	        assertEquals("deleted Successfully", result);
//	    }
//
//	    @Test
//	    public void testDeleteByEmployeeId() {
//	        // Given
//	        String employeeId = "123";
//	        Employee employee = new Employee();
//	        when(employeeRepository.findByEmployeeIdAndActive(employeeId,active)).thenReturn(employee);
//	        when(employeeRepository.deleteByEmployeeIdAndActive(employeeId,active)).thenReturn(Optional.of(1));
//
//	        // When
//	        Integer result = employeeService.deleteByEmployeeId(employeeId);
//
//	        // Then
//	        assertEquals(Integer.valueOf(1), result);
//	    }
//
//	   
//}
