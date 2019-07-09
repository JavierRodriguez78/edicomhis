package com.edicom.hisedicom.shared;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

	@Value("${keysecret}")
	private String keyApi;

	
	public String getKeyApi() {
		return keyApi;
	}

	public void setKeyApi(String keyApi) {
		this.keyApi = keyApi;
	}
	
	
	
}
