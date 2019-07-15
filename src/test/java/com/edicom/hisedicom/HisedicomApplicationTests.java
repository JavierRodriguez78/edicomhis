package com.edicom.hisedicom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=HisedicomApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class HisedicomApplicationTests {

	@Test
	public void contextLoads() {
	}

}
