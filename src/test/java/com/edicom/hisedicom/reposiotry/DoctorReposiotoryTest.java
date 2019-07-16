package com.edicom.hisedicom.reposiotry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.edicom.hisedicom.HisedicomApplication;
import com.edicom.hisedicom.doctors.domain.dao.IDoctorDao;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;
import com.edicom.hisedicom.patients.domain.entities.Patient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorReposiotoryTest {

	
	
@Autowired
private IDoctorDao doctorRepository;

	@Test
	public void injectedComponentsAreNotNull()
	{
		assertThat(doctorRepository).isNotNull();
	}
	
	@Test
	public void getDoctorByCollegeNumber()
	{
		Doctor doctor = doctorRepository.getByCollegiateNumber("1");
		assertThat(doctor).isNull();
	}
}
