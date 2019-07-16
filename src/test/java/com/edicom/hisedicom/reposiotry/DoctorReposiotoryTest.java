package com.edicom.hisedicom.reposiotry;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.edicom.hisedicom.HisedicomApplication;
import com.edicom.hisedicom.doctors.domain.dao.IDoctorDao;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;
import com.edicom.hisedicom.patients.domain.entities.Patient;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DoctorReposiotoryTest {

	
	
@Autowired
private IDoctorDao doctorRepository;

	@Test
	
	@Order(1)
	public void injectedComponentsAreNotNull()
	{
		assertThat(doctorRepository).isNotNull();
	}
	
	@Test
	@Order(2)
	public void getDoctorByCollegeNumber()
	{
		Doctor doctor = doctorRepository.getByCollegiateNumber("1");
		assertThat(doctor).isNull();
	}
	
	@Test
	public void addDoctor()
	{
		Doctor doctor = new Doctor();
		doctor.setName("Xavi");
		doctor.setLastname("Rodriguez");
		doctor.setCreatedAt(new Date());
		doctor.setCollegiatenumber("1");
		doctor.setSpecialty("General");
		doctorRepository.save(doctor);
		assertThat(doctor.getId()).isEqualTo(1);
	}
	
	@Test
	public void aupdateDoctor()
	{
		Doctor doctor;
		doctor = doctorRepository.getById((long)1);
		doctor.setName("modificadooooo");
		doctorRepository.update(doctor);
		doctor = doctorRepository.getById((long)1);
		assertThat(doctor.getName()).isEqualTo("modificadooooo");
	}
	@Test
	@Order(4)
	public void deleteDoctor()
	{
		int result = doctorRepository.deleteByCollegiateNumber("1");
	     assertThat(result).isEqualTo(1);
	}
	
	
}
