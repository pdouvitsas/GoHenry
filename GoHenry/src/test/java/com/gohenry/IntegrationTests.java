package com.gohenry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.gohenry.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;

	
	@Test
	public void createPerson_WithChildren() throws ParseException {
		
		Person child = new Person(new Long(2), null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", null, null);
		
		Person person = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null,
				Arrays.asList(child));

		HttpEntity<Person> request = new HttpEntity<>(person);
		Person responsePerson = testRestTemplate.postForObject("/parents", request, Person.class);
		
		assertThat(responsePerson.getFirstName()).isEqualTo("Jane");
		assertThat(responsePerson.getLastName()).isEqualTo("Doe");
		assertThat(responsePerson.getChildren().size()).isEqualTo(1);
		assertThat(responsePerson.getChildren().get(0).getEmailAddress()).isEqualTo("janet.doe@gohenry.co.uk");

	}
}
