package com.edicom.hisedicom.domain.dao;

import org.springframework.data.repository.CrudRepository;

import com.edicom.hisedicom.domain.entities.Patient;

public interface IPatientDao extends CrudRepository<Patient, Long>{

	
	
}
