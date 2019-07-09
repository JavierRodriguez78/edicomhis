package com.edicom.hisedicom.patients.domain.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edicom.hisedicom.patients.domain.entities.Patient;

public interface IPatientDao extends CrudRepository<Patient, Long>{

	
	public Optional<Patient> findByMedicalRecord(String medicalRecord);

	public Long deleteByMedicalRecord(String medicalRecod);
}
