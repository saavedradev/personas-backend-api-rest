package com.saavedradev.personas.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.saavedradev.personas.backend.apirest.models.entity.Person;
import com.saavedradev.personas.backend.apirest.models.services.IPersonService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class PersonRestController {

	@Autowired
	private IPersonService personService;

	@GetMapping("/people")
	public List<Person> index() {
		return personService.findAll();

	}

	@GetMapping("/people/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		Person person = null;
		Map<String, Object> response = new HashMap<>();
		try {
			person = personService.findByIdentification(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (person == null) {
			response.put("mensaje", "La persona no existe en la bd");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@PostMapping("/people")
	public ResponseEntity<?> create(@Valid @RequestBody Person person, BindingResult result) {
		Person personNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			personNew = personService.save(person);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La persona ha sido creada con ??xito");
		response.put("persona", personNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/people/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			personService.delete(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la persona de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		response.put("mensaje", "La persona ha sido eliminada con ??xito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/people/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Person person, BindingResult result, @PathVariable Long id) {
		Person personActual = personService.findById(id);
		Person personUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() +"' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);	
		}
		
		if( personActual == null) {
			response.put("mensaje", "Error: no se puede editar, la persona con ID: ".concat(id.toString().concat(" no existe en la bd!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
		}
		try {
			personActual.setIdentification(person.getIdentification());	
			personActual.setFullName(person.getFullName());
			personActual.setGender(person.getGender());
			personActual.setMotherId(person.getMotherId());
			personActual.setBirth(person.getBirth());
			personUpdate = personService.save(personActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el persona en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}

		response.put("mensaje", "La persona ha sido actualizado con ??xito!");
		response.put("persona", personUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
	}
	
	
	@PutMapping("/adopt/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adopt(@Valid @RequestBody Person person, BindingResult result, @PathVariable Long id) {
		Person childPerson = personService.findById(id);
		Person childUpdate = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() +"' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);	
		}
		if( childPerson == null) {
			response.put("mensaje", "Error: no se puede adoptar, la persona con ID: ".concat(id.toString().concat(" no existe en la bd!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
		}
		
		try {
			if(person.getGender().equals("M")) {
				childPerson.setFatherId(person.getIdentification());
			}
			if(person.getGender().equals("F")) {
				childPerson.setMotherId(person.getIdentification());		
			}
			childUpdate = personService.save(childPerson);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el persona en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}

		
		response.put("mensaje", "Ha sido adoptado");
		response.put("child", childUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
		
	}

}
