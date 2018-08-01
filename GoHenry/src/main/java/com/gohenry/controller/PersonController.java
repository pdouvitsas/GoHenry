package com.gohenry.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gohenry.domain.CreatePersonDto;
import com.gohenry.domain.PersonDto;
import com.gohenry.entity.Person;
import com.gohenry.service.PersonService;
import com.gohenry.utils.Utils;

@RestController
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService, ModelMapper modelMapper) {
		this.personService = personService;
	}

	@RequestMapping(value = "/parents", method = RequestMethod.POST)
	public ResponseEntity<?> savePerson(HttpServletRequest request, @RequestBody final CreatePersonDto createPersonDto ) throws ParseException {
		Long personId = personService.saveOrUpdate(convertToEntity(createPersonDto)).getId();
		return ResponseEntity.ok(personId);

	}

	@RequestMapping(value = "/parents/{id}", method = RequestMethod.GET)
	public PersonDto getPerson(HttpServletRequest request, @PathVariable Long id ) throws ParseException {
		Person person = personService.findById(id);
		if (person == null) {
			throw new PersonNotFoundException();
		}
		return convertToDto(person);
	}
	
	
	private PersonDto convertToDto(Person person) throws ParseException {
		ModelMapper modelMapper = new ModelMapper();
		PersonDto personDto = modelMapper.map(person, PersonDto.class);
		return personDto;
	}
	
	private Person convertToEntity(CreatePersonDto personDto) throws ParseException {
		ModelMapper modelMapper = new ModelMapper();
		Person person = modelMapper.map(personDto, Person.class);
	    person.setBirthDate(Utils.convertStringToDate(personDto.getDateOfBirth()));
	    if (personDto.getParentId() != null) {
	    	Person parent = personService.findById(personDto.getParentId());
	    	 person.setParent(parent);
	    }
	   
	    return person;
	}

}
