package com.freelancer.assetmanagement.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.freelancer.assetmanagement.filter.JwtAuthFilter;
import com.freelancer.assetmanagement.service.EmployeeService;
import com.freelancer.assetmanagement.service.EmployeeServiceImp;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableSwagger2
public class SecurityConfig {
	
	@Autowired
	protected JwtAuthFilter jwtAuthFilter;

	@Bean
	UserDetailsService userDetailsService() {
//		UserDetails admin = User.withUsername("Krishna")
//		.password(encoder.encode("k1234"))
//		.roles("ADMIN")
//		.build();
//		
//		UserDetails user = User.withUsername("Nitish")
//				.password(encoder.encode("n1234"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
		return new EmployeeServiceImp();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("employee/authenticate","employee/postEmployee","organization/postOrganization","employee/deleteByEmployeeId","asset/saveAsset","swagger-ui","/v3/**","/v2/api-docs","/configuration/ui","/swagger-resources/**","/configuration/security","/swagger-ui.html","swagger-ui/**","/webjars/**","/v2/**").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("employee/getEmployeeById","asset/getAssetByEmployeeId","employee/getAllEmployees","employee/updateEmployee","employee/deleteEmployee","organization/getAllOrganizations","organization/getOrganizationById","organization/updateOrganization","organization/deleteOrganization","asset/**","fixedAsset/**","ITAsset/**","asset/saveAssetWithFixed","asset/saveAssetWithIT").authenticated()

				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
//	.and().formLogin().and().build();
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}