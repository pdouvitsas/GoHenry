package com.gohenry.utils;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gohenry.domain.PersonDto;
import com.gohenry.entity.Person;

@Component
public class DtoUtils {
	
	@Autowired
    private static ModelMapper modelMapper;
	
	public static PersonDto convertToDto(Person person) throws ParseException {
		PersonDto personDto = modelMapper.map(person, PersonDto.class);
		personDto.setDateOfBirth(Utils.convertDateToString(person.getDateOfBirth()));
	    return personDto;
	}
	
	public static Person convertToEntity(PersonDto personDto) throws ParseException {
	    Person person = modelMapper.map(personDto, Person.class);
	    person.setDateOfBirth(Utils.convertStringToDate(personDto.getDateOfBirth()));
	 
	    return person;
	}
}
