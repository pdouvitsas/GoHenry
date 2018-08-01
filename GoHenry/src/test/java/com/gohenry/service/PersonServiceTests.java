package com.gohenry.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gohenry.controller.PersonNotFoundException;
import com.gohenry.dao.PersonRepository;
import com.gohenry.entity.Person;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTests {

	@Mock
	private PersonRepository personRepository;

	private PersonService personService;

	private Person child = null;
	private Person person = null;
	
	@Before
	public void setUp() throws Exception {
		personService = new PersonService(personRepository);
		
		child = new Person(new Long(2), null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", null, null);
		
		person = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null,
				Arrays.asList(child));
		
	}
	
	@Test
	public void savePerson() {
		
		given(personRepository.save(person)).willReturn(person);

		Person resultPerson = personService.saveOrUpdate(person);

		assertThat(resultPerson.getLastName()).isEqualTo("Doe");
		assertThat(resultPerson.getFirstName()).isEqualTo("Jane");
	}
	
	@Test
	public void getPersonDetails_returnsPersonInfo() {
		given(personRepository.findById(new Long(1))).willReturn(person);

		Person personResult = personService.findById(new Long(1));

		assertThat(personResult.getFirstName()).isEqualTo("Jane");
		assertThat(personResult.getLastName()).isEqualTo("Doe");
	}


}
