package com.edicom.hisedicom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edicom.hisedicom.aplication.security.JWTAuthorizationFilter;
import com.edicom.hisedicom.shared.Properties;

@SpringBootApplication
public class HisedicomApplication {

	@Autowired
	Properties properties;
	
	public static void main(String[] args) {
		SpringApplication.run(HisedicomApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter
	{
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.csrf().disable()
			 .addFilterAfter(new JWTAuthorizationFilter(properties), UsernamePasswordAuthenticationFilter.class)
			 .authorizeRequests()
			 .antMatchers(HttpMethod.POST, "/auth").permitAll()
			 .anyRequest().authenticated();
		}
	}

}
