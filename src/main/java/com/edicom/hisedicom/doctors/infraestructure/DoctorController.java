package com.edicom.hisedicom.doctors.infraestructure;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edicom.hisedicom.doctors.application.components.DoctorComponent;
import com.edicom.hisedicom.doctors.application.services.IDoctorService;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;

@RestController
@RequestMapping("doctors")
public class DoctorController {

	private static final org.apache.commons.logging.Log logger = LogFactory.getLog("DoctorController.class");
	@Autowired
	private IDoctorService doctorService;
	@Autowired
	private DoctorComponent doctorComponent;
	
	
	@RequestMapping(value="",method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Doctor>>getDoctors()
	{
		List<Doctor> list = doctorComponent.getDoctors();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id)
	{
		
		return new ResponseEntity<>(doctorService.getDoctor(id),HttpStatus.OK); 
	}
	@RequestMapping(value="/{collegiatednumber}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable String collegiatednumber)
	{
		logger.debug(doctorService.getDoctorByCollegiatedNumber(collegiatednumber));
		
		return new ResponseEntity<>(doctorService
				.getDoctorByCollegiatedNumber(collegiatednumber),HttpStatus.OK); 
	}
	
	@RequestMapping(value="",method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<Doctor>saveDoctor(@RequestBody Doctor doctor, UriComponentsBuilder builder)
	{
		doctorService.saveDoctor(doctor);
		return new ResponseEntity(doctor, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{collegiatednumber}",method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Void>deleteDoctor(@PathVariable String collegiatednumber)
	{
		if(doctorService.deleteDoctor(collegiatednumber))return new ResponseEntity(HttpStatus.OK);
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT, produces="application/json")
	public ResponseEntity<Doctor>updateDoctor(@RequestBody Doctor doctor)
	{
		return new ResponseEntity(doctorService.updateDoctor(doctor), HttpStatus.OK);
	}
	
}
