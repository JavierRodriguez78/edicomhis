package com.edicom.hisedicom.domain.dao;

import java.util.List;

import com.edicom.hisedicom.domain.entities.Doctor;

public interface IDoctorDao {
	
	List<Doctor> getAll();
	Doctor getById(Long id);
	Doctor getByCollegiateNumber(String collegiateNumber);
	void save(Doctor doctor);
	int deleteByCollegiateNumber(String collegiateNumber);
	void update(Doctor doctor);
}
