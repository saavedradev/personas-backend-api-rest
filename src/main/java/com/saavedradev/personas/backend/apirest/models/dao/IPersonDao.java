package com.saavedradev.personas.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.saavedradev.personas.backend.apirest.models.entity.Person;

public interface IPersonDao extends CrudRepository<Person, Long>{
	
	  @Query(value = "SELECT * FROM people WHERE identification = ?1", nativeQuery = true)
	  Person findByIdentification(String identification);

}
