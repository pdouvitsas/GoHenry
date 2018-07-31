package com.gohenry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gohenry.dao.PersonRepository;
import com.gohenry.entity.Person;

@Service
public class PersonService {
	
	private PersonRepository personRepository;
	
	@Autowired
	public PersonService(final PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Person saveOrUpdate(Person person) {
		return personRepository.save(person);
	}
	
	public Person findById(Long id) {
		return personRepository.findById(id);
	}

}
