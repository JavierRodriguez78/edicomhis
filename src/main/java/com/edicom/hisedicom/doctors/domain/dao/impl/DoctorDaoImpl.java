package com.edicom.hisedicom.doctors.domain.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.edicom.hisedicom.doctors.domain.dao.IDoctorDao;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;


@Transactional
@Repository
public class DoctorDaoImpl implements IDoctorDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Doctor> getAll() {
		String hql= "FROM Doctor";
		return (List<Doctor>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Doctor getById(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Doctor.class, id);
	}

	@Override
	public Doctor getByCollegiateNumber(String collegiateNumber) {
		// TODO Auto-generated method stub
		String hql = "FROM Doctor as doctor where doctor.collegiatenumber=?1";
	
			List<Doctor> doctor=	 entityManager.createQuery(hql)
				.setParameter(1,collegiateNumber).getResultList();
			return 	(doctor.size()>1)? doctor.get(0): null;
	}

	@Override
	public void save(Doctor doctor) {
		// TODO Auto-generated method stub
		entityManager.persist(doctor);
		
	}

	@Override
	public int deleteByCollegiateNumber(String collegiateNumber) {
		// TODO Auto-generated method stub
//		try {
//			Doctor result = this.getByCollegiateNumber(collegiateNumber);
//			entityManager.remove(result);
//		}catch (Exception e) {
//			return 0;
//		}
//		return 1 ;
		
		try {
		String hql="delete from Doctor as doctor where doctor.collegiatenumber=?1";
		return entityManager.createQuery(hql).setParameter(1, collegiateNumber).executeUpdate();
		
		}catch (Throwable t)
		{
		   entityManager.getTransaction().rollback();
			return 0;
		}
		
		
		
		
	}

	@Override
	public void update(Doctor doctor) {
		entityManager.merge(doctor);
	}

}
