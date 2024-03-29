package com.edicom.hisedicom.aplication.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edicom.hisedicom.shared.Properties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER="Authorization";
	private final String PREFIX="Bearer ";

	private Properties properties;
	
	private  String SECRET ;
	
	@Autowired
	public JWTAuthorizationFilter(Properties properties)
	{
		this.properties=properties;
		this.SECRET= properties.getKeyApi();
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if(this.existJWT(request, response)) {
				Claims claims = this.validateJWT(request);
				if(claims.get("authorities")!=null){
					this.setUpSpringAuthentication(claims);
				}else {
					SecurityContextHolder.clearContext();
				}
						
			}
			filterChain.doFilter(request, response);
		}catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
		
		
	}
	
	private void setUpSpringAuthentication(Claims claims) {
		// TODO Auto-generated method stub
		List<String> authorities= (List<String>) claims.get("authorities");
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private Claims validateJWT(HttpServletRequest req)
	{
		String jwToken = req.getHeader(HEADER).replace(PREFIX," ");
		
		return Jwts.parser().setSigningKey(SECRET.getBytes())
				.parseClaimsJws(jwToken).getBody();
	}
	
	private boolean existJWT(HttpServletRequest req, HttpServletResponse res)
	{
		String authenticationHeader = req.getHeader(HEADER);
		if(authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

	
}
