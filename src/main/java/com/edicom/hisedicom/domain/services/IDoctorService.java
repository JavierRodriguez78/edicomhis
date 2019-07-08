package com.edicom.hisedicom.domain.services;

import java.util.List;

import com.edicom.hisedicom.domain.entities.Doctor;

public interface IDoctorService {
	List<Doctor> getDoctors();
	Doctor getDoctor(Long id);
	Doctor getDoctorByCollegiatedNumber(String collegiatedNumber);
	void saveDoctor(Doctor doctor);
	boolean deleteDoctor(String collegiateNumber);
	Doctor updateDoctor(Doctor doctor);
}
