package com.edicom.hisedicom.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edicom.hisedicom.aplication.entities.User;

@RestController
public class AuthController {

	@RequestMapping(value="auth", method=RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestBody User user)
	{
		
		return null;
	}
}
