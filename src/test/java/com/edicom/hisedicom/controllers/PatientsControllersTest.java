package com.edicom.hisedicom.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
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
	
	
	private  Doctor doctor = new Doctor();
	private  Patient patient= new Patient();
	
	@Before
	public  void setupData() {
		Date fecha = new Date();
		this.doctor.setName("Pepe");
		this.doctor.setCreatedAt(fecha);
		this.doctor.setSpecialty("General");
		this.doctor.setLastname("Muños");
		this.doctor.setCollegiatenumber("ldjldfjl");
		//this.doctorService.saveDoctor(doctor);
		this.patient.setName("xavi");
		this.patient.setLastname("Rodriguez");
		this.patient.setDoctor(doctor);
		this.patient.setCreatedAt(fecha);
		this.patient.setMedicalRecord("1");
		//this.patientService.savePatient(patient);
	}
		
	   @Before
		public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	

	@Test
	public void returnForbiddenisNotAuthenticated() throws Exception{
		
		mvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
	
		.andDo(print())
		.andExpect(status().isForbidden());
		
	}
	
	@WithMockUser(username="xavi1", password="123", roles="USER")
	@Test
	public void returnNotFoundisUserNotExist() throws Exception{
		
		mvc.perform(get("/patients").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@WithMockUser(username="xavi", password="123", roles="USER")
	@Test
	public void createNewPatient() throws Exception{
		
		
		mvc.perform(post("/patients").contentType(MediaType.APPLICATION_JSON).content(this.toJson(this.patient)))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name").value(this.patient.getName()));
		
		List<Patient> found = patientService.getPatients();
		assertThat(found).extracting(Patient::getName).contains("xavi");
		
		
	}
	
	@WithMockUser(username="xavi", password="123", roles="USER")
	@Test
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
	
	
	static byte[] toJson(Object object) throws IOException
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		
		return gson.toJson(object).getBytes();
	}
	
	static String toDateString (Date fecha) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
		return sm.format(fecha);
		}
	
	
}
