package com.edicom.hisedicom.aplication.services;

import javax.servlet.http.HttpServletRequest;

public interface IServiceJWT {

	String getJWT(String username, HttpServletRequest request);
}
