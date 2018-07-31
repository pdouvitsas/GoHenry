package com.gohenry.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gohenry.domain.PersonDto;
import com.gohenry.entity.Person;
import com.gohenry.service.PersonService;
import com.gohenry.utils.DtoUtils;

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

	@RequestMapping(value = "/parents/{id}", method = RequestMethod.GET)
	public PersonDto savePerson(HttpServletRequest request, @PathVariable Long id ) throws ParseException {
		Person person = personService.findById(id);
		return DtoUtils.convertToDto(person);
	}
	

}
