package com.edicom.hisedicom.patients.application;

import java.rmi.server.UID;

import org.springframework.stereotype.Service;

@Service
public class GeneralService {

	public String MedicalRecordGen() {
		UID uid = new UID();
		return "med-" + uid;
	}

}
