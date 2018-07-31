package com.gohenry.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gohenry.entity.Person;

@Service
public class PersonService {
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Person saveOrUpdate(Person person) {
		return person;
	}

}
