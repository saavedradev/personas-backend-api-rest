package com.saavedradev.personas.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saavedradev.personas.backend.apirest.models.entity.Person;
import com.saavedradev.personas.backend.apirest.models.services.IPersonService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PersonRestController {
	
	@Autowired
	private IPersonService personService;
	
	@GetMapping("/people")
	public List<Person> index(){
		return personService.findAll();
		
	}

}
