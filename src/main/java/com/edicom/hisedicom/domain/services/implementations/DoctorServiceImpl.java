package com.edicom.hisedicom.domain.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edicom.hisedicom.domain.dao.IDoctorDao;
import com.edicom.hisedicom.domain.entities.Doctor;
import com.edicom.hisedicom.domain.services.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService{

	@Autowired
	private IDoctorDao doctorDao;
	@Override
	public List<Doctor> getDoctors() {
		// TODO Auto-generated method stub
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
