package com.edicom.hisedicom.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edicom.hisedicom.domain.entities.Patient;



public interface IPatientService {

	public List<Patient> getPatients();
	public Optional<Patient> getPatient(String medicalRecord);
	public void savePatient(Patient patient);
	public Optional<Patient> updatePatient(Patient patient);
	public void deletePatient(String medicalReport);
}
