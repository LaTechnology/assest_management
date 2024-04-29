package com.freelancer.assetmanagement.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.freelancer.assetmanagement.service.EmployeeServiceImp;
import com.freelancer.assetmanagement.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	Logger log=LoggerFactory.getLogger(JwtAuthFilter.class);

	@Autowired
	protected JwtService jwtService;
	
	@Autowired
	protected EmployeeServiceImp employeeServiceImp;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader=request.getHeader("Authorization");
		log.info("authHeader in jwtAuthFilter-> {}",authHeader);
		String token=null;
		String emailId=null;
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			log.info("token -> {}",token);
			emailId=jwtService.extractUserName(token);
		}
		
		if(emailId!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = employeeServiceImp.loadUserByUsername(emailId);
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(emailId,userDetails.getPassword(), userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}

