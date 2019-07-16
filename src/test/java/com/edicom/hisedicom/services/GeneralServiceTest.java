package com.edicom.hisedicom.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edicom.hisedicom.patients.application.GeneralService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneralServiceTest {

	@Autowired
	private GeneralService generalService;
	
	@Test
	public void testMedicalRecordGen() {
		String result = generalService.MedicalRecordGen();
		
		assertThat(result).contains("med0-");
	}
	
}
