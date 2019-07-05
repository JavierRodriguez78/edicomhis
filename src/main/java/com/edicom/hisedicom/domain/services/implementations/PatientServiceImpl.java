package com.edicom.hisedicom.domain.services.implementations;

import java.util.List;
import java.util.Optional;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edicom.hisedicom.domain.dao.IPatientDao;
import com.edicom.hisedicom.domain.entities.Patient;
import com.edicom.hisedicom.domain.services.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{

	@Autowired
	private IPatientDao patientDao;

	private static final Log logger = LogFactory.getLog("PatientServiceImpl.class");
	
	@Override
	@Transactional(readOnly=true)
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		return (List<Patient>)patientDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Patient> getPatient(String medicalRecord) {
		// TODO Auto-generated method stub
		    return patientDao.findByMedicalRecord(medicalRecord);
	}

	@Override
	public Patient savePatient(Patient patient){
		
		try {
			return patientDao.save(patient);
		}catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		}
		
	}

	@Override
	public Patient updatePatient(Patient patient) {
		
		
		if(patient.getId()==null) return null;
		
		try {
			return patientDao.save(patient);
		
			
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public boolean deletePatient(String medicalRecord) {
		
		
		try {
		 if(patientDao.deleteByMedicalRecord(medicalRecord)==1) return true;
		 return false;
		 } catch(Exception e) {
			 logger.error(e.getMessage());
			 return false;
		 } 
		
	}

}
