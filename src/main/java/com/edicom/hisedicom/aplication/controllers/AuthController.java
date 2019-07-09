package com.edicom.hisedicom.aplication.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edicom.hisedicom.aplication.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController {

	@Value("${keysecret}")
	private String secretKey;
	
	@RequestMapping(value="auth", method=RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestBody User user)
	{
		String token= this.getJWT("xavi");
		return new ResponseEntity(token, HttpStatus.OK);
	}
	
	private String getJWT(String username) {
		//String secretKey="bragasdeesparto";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts
				.builder()
				.setId("edicom")
				.setSubject(username)
				.claim("authorities", grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				 .setIssuedAt(new Date(System.currentTimeMillis()))
				 .setExpiration(new Date(System.currentTimeMillis() + 6000000))
				 .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
									
	
		return "Bearer "+ token;
	}
	
}
