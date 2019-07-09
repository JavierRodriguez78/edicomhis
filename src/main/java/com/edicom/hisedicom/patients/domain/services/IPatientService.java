package com.edicom.hisedicom.patients.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edicom.hisedicom.patients.domain.entities.Patient;



public interface IPatientService {

	public List<Patient> getPatients();
	public Optional<Patient> getPatient(String medicalRecord);
	public Patient savePatient(Patient patient);
	public Patient updatePatient(Patient patient);
	public boolean deletePatient(String medicalReport);
}
