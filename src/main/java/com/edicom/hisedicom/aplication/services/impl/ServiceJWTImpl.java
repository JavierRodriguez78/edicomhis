package com.edicom.hisedicom.aplication.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.edicom.hisedicom.aplication.services.IServiceJWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ServiceJWTImpl implements IServiceJWT {

	@Value("${keysecret}")
	private String secretKey;
	
	@Override
	public String getJWT(String username, HttpServletRequest request) {
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
						.commaSeparatedStringToAuthorityList("ROLE_USER");
				String token = Jwts
						.builder()
						.claim("ip",request.getRemoteAddr())
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
