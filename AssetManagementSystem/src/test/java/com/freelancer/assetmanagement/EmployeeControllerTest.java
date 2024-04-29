//package com.freelancer.assetmanagement;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.freelancer.assetmanagement.controller.EmployeeController;
//import com.freelancer.assetmanagement.service.EmployeeService;
//
//
////@WebMvcTest(EmployeeController.class)
//public class EmployeeControllerTest {
//
////	@Autowired
////	private MockMvc mvc;
////	
////	@Autowired
////	private EmployeeService employeeService;
////	
////	@Test
////	public void getAllEmlployeeAPI() {
////		try {
////			ResultActions andExpect = mvc.perform(MockMvcRequestBuilders
////					.get("employee/getAllEmployees")
////					.accept(org.springframework.http.MediaType.APPLICATION_JSON))
////			  .andDo(print())
////			  .andExpect(status().isOk())
////			  .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
////			  .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
////			System.out.println("ResultActions->"+andExpect);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//}
