package com.edicom.hisedicom.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@RequestMapping(value="patients/{medicalrecord}", method=RequestMethod.GET, 
			produces="application/json")
	public ResponseEntity<Patient> getPatient(@PathVariable String medicalrecord)
	{
		Optional<Patient> patient = patientService.getPatient(medicalrecord);
		
		return patient.map(o -> new ResponseEntity<Patient>(o, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
//		return (!patient.isPresent())? 
//				new ResponseEntity<>(HttpStatus.NOT_FOUND)
//				:new ResponseEntity<>(patient.get(),HttpStatus.OK);
	}
	
	@RequestMapping(value="patients/{medicalrecord}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePatient(@PathVariable String medicalrecord)
	{
		Boolean result = patientService.deletePatient(medicalrecord);
		
		return (!result)? new ResponseEntity<>(HttpStatus.NOT_MODIFIED) 
				: new ResponseEntity<>(HttpStatus.OK); 
		
	}
	
	@RequestMapping(value="patients", method=RequestMethod.POST, 
			produces="application/json")
	public ResponseEntity<Patient> savePatient (@RequestBody Patient patient)
	{
		Patient result = patientService.savePatient(patient);
		return (result==null)?new ResponseEntity<>(HttpStatus.BAD_REQUEST)
				:new ResponseEntity<>(result, HttpStatus.CREATED);
	
	}
	
	@RequestMapping(value="patients", method=RequestMethod.PUT, 
			produces="application/json")
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient)
	{
		Patient result= patientService.updatePatient(patient);
		return (result==null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
}
