package com.freelancer.assetmanagement.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	Logger log=LoggerFactory.getLogger(JwtService.class);
	private static final String SECRET="DEXsw6+bHeLpEBasVG2UU1nR6RX+Dn4z3fUVRMzptKw6JTScBO/n07DiEHinLZH8";
	
	public String extractUserName(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token,Claims::getExpiration);
	}
	
	public <T> T extractClaims(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String userName=extractUserName(token);
		log.info("userName.equals(userDetails.getUsername()) && ! isTokenExpired(token)-> {}",(userName.equals(userDetails.getUsername()) && ! isTokenExpired(token)));
		return userName.equals(userDetails.getUsername()) && ! isTokenExpired(token);
	}
	
	public String generateToken(String userName) {
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,userName);
	}
	
	public String createToken(Map<String,Object> claims,String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}
	
	public Key getSignKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
}
