package com.saavedradev.personas.backend.apirest.models.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saavedradev.personas.backend.apirest.models.dao.IPersonDao;
import com.saavedradev.personas.backend.apirest.models.entity.Person;

@Service
public class PersonServiceImpl implements IPersonService{
	
	@Autowired
	private IPersonDao personDao;

	@Override
	@Transactional(readOnly = true)
	public List<Person> findAll() {
		return (List<Person>) personDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Person findById(Long id) {
		return personDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Person save(Person person) {
		return personDao.save(person);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		personDao.deleteById(id);
	}

}
