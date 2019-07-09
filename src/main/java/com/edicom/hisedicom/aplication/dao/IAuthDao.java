package com.edicom.hisedicom.aplication.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edicom.hisedicom.aplication.entities.User;
import com.edicom.hisedicom.patients.domain.entities.Patient;

public interface IAuthDao extends CrudRepository<User, Long>{
	
	public Optional<User> findByUsername(String UserName);
}
