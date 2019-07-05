package com.edicom.hisedicom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edicom.hisedicom.domain.entities.Patient;
import com.edicom.hisedicom.domain.services.IPatientService;

@RestController
@RequestMapping("api/v1")
public class PatientController {

	@Autowired
	private IPatientService patientService;
	@RequestMapping(value="hello", method= RequestMethod.POST, produces="application/json")
	public ResponseEntity<Void> HelloWorld()
	{
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="patients", method= RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Patient>> getPatients()
	{
		List<Patient> list = patientService.getPatients();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
}
