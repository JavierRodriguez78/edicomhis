package com.edicom.hisedicom.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.edicom.hisedicom.HisedicomApplication;
import com.edicom.hisedicom.doctors.application.services.IDoctorService;
import com.edicom.hisedicom.doctors.application.services.impl.DoctorServiceImpl;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT, classes= HisedicomApplication.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private WebApplicationContext context;
	
	   @Before
		public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@Test
	public void notAuthorizegetDoctors() throws Exception{
		
		mvc.perform(get("/doctors").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username="xavi1", password="123", roles="USER")
	public void AuthorizedGetDoctors() throws Exception{
		mvc.perform(get("/doctors").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="xavi1", password="123", roles="USER")
	public void updateDoctor() throws Exception {
		Doctor doctor = new Doctor();
		doctor.setName("Doctor1");
		doctor.setLastname("Doctor1");
		doctor.setCreatedAt(new Date());
		doctor.setSpecialty("General");
		doctor.setCollegiatenumber("2");
		this.doctorService.saveDoctor(doctor);
		
		List<Doctor>  data = this.doctorService.getDoctors();
		data.get(0).setLastname("modificado");
			
		mvc.perform( put("/doctors/").contentType(MediaType.APPLICATION_JSON).content(this.toJson(data.get(0))))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	

	@Test
	@WithMockUser(username="xavi1", password="123", roles="USER")
	public void deleteDoctor() throws Exception {
		Doctor doctor = new Doctor();
		doctor.setName("Doctor1");
		doctor.setLastname("Doctor1");
		doctor.setCreatedAt(new Date());
		doctor.setSpecialty("General");
		doctor.setCollegiatenumber("3");
		this.doctorService.saveDoctor(doctor);
		
		List<Doctor>  data = this.doctorService.getDoctors();
		
			
		mvc.perform( delete("/doctors/"+data.get(0).getCollegiatenumber()).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	@WithMockUser(username="xavi1", password="123", roles="USER")
	public void CreateDoctors() throws Exception{
		
		Doctor doctor = new Doctor();
		doctor.setName("Doctor1");
		doctor.setLastname("Doctor1");
		doctor.setCreatedAt(new Date());
		doctor.setSpecialty("General");
		doctor.setCollegiatenumber("1");
		mvc.perform(post("/doctors").contentType(MediaType.APPLICATION_JSON).content(this.toJson(doctor)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value(doctor.getName()));
		
		List<Doctor> found = doctorService.getDoctors();
		assertThat(found).extracting(Doctor::getName).contains("Doctor1");		
	}
	
	
	
	
	
	static byte[] toJson(Object object) throws Exception
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		
		return gson.toJson(object).getBytes();
	}

}
