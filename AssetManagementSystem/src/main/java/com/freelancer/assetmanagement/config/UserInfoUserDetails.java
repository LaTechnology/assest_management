package com.freelancer.assetmanagement.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.freelancer.assetmanagement.model.Employee;



public class UserInfoUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3901133846513917053L;
//	private String name;
	private String emailId;
	private String password;
	private List<GrantedAuthority> authorities;
	
	

	public UserInfoUserDetails(Employee employee) {
//		name=employee.getFirstName()+" "+employee.getLastName();
		emailId=employee.getEmailId();
		password=employee.getPassword();
		authorities=employee.getRole().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

