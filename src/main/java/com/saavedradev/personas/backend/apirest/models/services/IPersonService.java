package com.saavedradev.personas.backend.apirest.models.services;

import java.util.List;

import com.saavedradev.personas.backend.apirest.models.entity.Person;

public interface IPersonService {
	
	public List<Person> findAll();
	
	public Person findById(Long id);
	
	public Person save(Person person);
	
	public void delete(Long id); 
	
	

}
