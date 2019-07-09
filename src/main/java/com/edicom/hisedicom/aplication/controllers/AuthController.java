package com.edicom.hisedicom.aplication.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edicom.hisedicom.aplication.dao.IAuthDao;
import com.edicom.hisedicom.aplication.entities.User;
import com.edicom.hisedicom.aplication.services.IServiceJWT;


@RestController
public class AuthController {

	@Autowired
	IServiceJWT serviceJWT;
	@Autowired
	IAuthDao authDao;
	
	@RequestMapping(value="auth", method=RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestBody User user, HttpServletRequest request)
	{
		
		Optional<User> result = authDao.findByUsername(user.getUsername());
		if (!result.isPresent()) return new ResponseEntity("Datos incorrectos", HttpStatus.BAD_REQUEST); 
		if(!user.getPassword().equals(result.get().getPassword()))return new ResponseEntity("Datos incorrectos", HttpStatus.BAD_REQUEST); 
	
		String token= serviceJWT.getJWT(user.getUsername(), request);
		result.get().setToken(token);
		authDao.save(result.get());
		return new ResponseEntity(token, HttpStatus.OK);
	}
	
	
	
}
