package com.edicom.hisedicom.doctors.application.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edicom.hisedicom.doctors.application.services.IDoctorService;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;

@Component
public class DoctorComponent {

	@Autowired
	private IDoctorService doctorService;
	
	public List<Doctor> getDoctors(){
		return doctorService.getDoctors();
	}


}
