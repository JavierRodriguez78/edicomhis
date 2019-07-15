package com.edicom.hisedicom.controllers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


@RunWith(SpringRunner.class)
@SpringBootTest(classes=HisedicomApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PatientsControllersTest {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mvc;
	
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
	
}
