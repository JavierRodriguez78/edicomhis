package com.edicom.hisedicom.doctors.application.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edicom.hisedicom.doctors.application.services.IDoctorService;
import com.edicom.hisedicom.doctors.domain.dao.IDoctorDao;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;

@Service
public class DoctorServiceImpl implements IDoctorService{
	
	private static final Log logger =  LogFactory.getLog("DoctorServiceImpl.class");
	
	@Autowired
	private IDoctorDao doctorDao;
	@Override
	public List<Doctor> getDoctors() {
		// TODO Auto-generated method stub
		logger.debug(doctorDao.getAll());
		return doctorDao.getAll();
	}
	
	public Doctor getDoctor(Long id) {
		return doctorDao.getById(id);
	}
	
	@Override
	public Doctor getDoctorByCollegiatedNumber(String collegiatedNumber) {
		// TODO Auto-generated method stub
		return doctorDao.getByCollegiateNumber(collegiatedNumber);
	}

	@Override
	public void saveDoctor(Doctor doctor) {
		doctorDao.save(doctor);
		
	}

	@Override
	public boolean deleteDoctor(String collegiateNumber) {
		int result = doctorDao.deleteByCollegiateNumber(collegiateNumber);
		if(result==0) return false;
		return true;
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {

		
		if(doctor.getId()==null) return null;
		
		try {
		  doctorDao.update(doctor);
		  return doctor;
		
			
		}catch (Exception e) {
			//logger.error(e.getMessage());
			return null;
		}
	}
	
	

}
