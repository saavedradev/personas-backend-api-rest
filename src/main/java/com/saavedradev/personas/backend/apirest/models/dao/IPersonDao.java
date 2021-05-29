package com.saavedradev.personas.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.saavedradev.personas.backend.apirest.models.entity.Person;

public interface IPersonDao extends CrudRepository<Person, Long>{

}
