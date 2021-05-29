package com.saavedradev.personas.backend.apirest.models.services;

import java.util.List;

import com.saavedradev.personas.backend.apirest.models.entity.Person;

public interface IPersonService {
	
	public List<Person> findAll();
	

}
