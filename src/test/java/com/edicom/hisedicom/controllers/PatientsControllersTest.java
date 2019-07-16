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
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.edicom.hisedicom.HisedicomApplication;
import com.edicom.hisedicom.doctors.application.services.IDoctorService;
import com.edicom.hisedicom.doctors.domain.entities.Doctor;
import com.edicom.hisedicom.patients.domain.entities.Patient;
import com.edicom.hisedicom.patients.domain.services.IPatientService;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minidev.json.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=HisedicomApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PatientsControllersTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private IDoctorService doctorService;
	
		
	   @Before
		public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	 

	@Test
	@Order(1)
	public void returnForbiddenisNotAuthenticated() throws Exception{
		
		mvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
	
		.andDo(print())
		.andExpect(status().isForbidden());
		
	}
	
	@WithMockUser(username="xavi1", password="123", roles="USER")
	@Test
	@Order(2)
	public void returnNotFoundisUserNotExist() throws Exception{
		
		mvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@WithMockUser(username="xavi", password="123", roles="USER")
	@Test
	@Order(3)
	public void createNewPatient() throws Exception{
		
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		Date fecha = new Date();
		doctor.setName("Pepe");
		doctor.setCreatedAt(fecha);
		doctor.setSpecialty("General");
		doctor.setLastname("Muños");
		doctor.setCollegiatenumber("ldjldfjl");
		//this.doctorService.saveDoctor(doctor);
		patient.setName("xavi");
		patient.setLastname("Rodriguez");
		patient.setDoctor(doctor);
		patient.setCreatedAt(fecha);
		patient.setMedicalRecord("1");
		
		
		
		mvc.perform(post("/patients").contentType(MediaType.APPLICATION_JSON).content(this.toJson(patient)))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name").value(patient.getName()));
		
		List<Patient> found = patientService.getPatients();
		assertThat(found).extracting(Patient::getName).contains("xavi");
		
		
	}
	
	@WithMockUser(username="xavi", password="123", roles="USER")
	@Test
	@Order(4)
	public void updatePatient() throws Exception
	{
		List<Patient> data = this.patientService.getPatients();
	     data.get(0).setLastname("prueba");
	
		System.out.println(data.toString());
		mvc.perform(put("/patients").contentType(MediaType.APPLICATION_JSON).content(this.toJson(data.get(0))))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.lastname").value("prueba"));
		
		List<Patient> found = patientService.getPatients();
		assertThat(found).extracting(Patient::getLastname).contains("prueba");
	}
	
	
	  @WithMockUser(username="xavi", password="123", roles="USER")
			@Test
			@Order(5)
			public void deletePatient() 
			{

			Doctor doctor = new Doctor();
			Patient patient = new Patient();
			Date fecha = new Date();
			doctor.setName("Pepe");
			doctor.setCreatedAt(fecha);
			doctor.setSpecialty("General");
			doctor.setLastname("Muños");
			doctor.setCollegiatenumber("ldjldfjl");
			this.doctorService.saveDoctor(doctor);
			patient.setName("xavi");
			patient.setLastname("Rodriguez");
			patient.setDoctor(doctor);
			patient.setCreatedAt(fecha);
			patient.setMedicalRecord("1");
		   // this.doctorService.saveDoctor(doctor);
		  	this.patientService.savePatient(patient);
				List<Patient> found = patientService.getPatients();
				System.out.println(found.get(0).toString());
//				mvc.perform(delete("/patients/"+ found.get(0).getMedicalRecord()).contentType(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk());
				
			
			}
	
	
	static byte[] toJson(Object object) throws IOException
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		
		return gson.toJson(object).getBytes();
	}
	
	
	
}
