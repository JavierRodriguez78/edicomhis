package com.edicom.hisedicom.domain.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edicom.hisedicom.domain.dao.IPatientDao;
import com.edicom.hisedicom.domain.entities.Patient;
import com.edicom.hisedicom.domain.services.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{

	@Autowired
	private IPatientDao patientDao;
	
	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		return (List<Patient>)patientDao.findAll();
	}

	@Override
	public Optional<Patient> getPatient(String medicalRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Patient> updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatient(String medicalReport) {
		// TODO Auto-generated method stub
		
	}

}
