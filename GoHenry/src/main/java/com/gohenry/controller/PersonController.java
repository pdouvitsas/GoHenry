package com.gohenry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gohenry.entity.Person;
import com.gohenry.service.PersonService;

@RestController
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/parents", method = RequestMethod.POST)
	public ResponseEntity<?> savePerson(HttpServletRequest request, @RequestBody final Person person ) {
		personService.saveOrUpdate(person);
		return ResponseEntity.ok().build();
	}

	

}
