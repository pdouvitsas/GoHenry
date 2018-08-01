package com.gohenry;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.gohenry.domain.CreatePersonDto;
import com.gohenry.domain.PersonDto;
import com.gohenry.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;

	private CreatePersonDto parentDto = null;
	private CreatePersonDto childDto = null;
	
	private Person parent = null;
	private Person child = null;
	
	@Before
	public void setUp() throws Exception {	
		parent = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null,
				Arrays.asList(child));
		child = new Person(new Long(2), null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", parent, null);
		
		parentDto = new CreatePersonDto("Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null);
		childDto = new CreatePersonDto(null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", new Long(1));
		
	}
	
	@Test
	public void createPerson_thenFetchPerson() throws ParseException {
		HttpEntity<CreatePersonDto> request = new HttpEntity<>(parentDto);
		Integer resultPersonId = (Integer) testRestTemplate.postForObject("/parents", request, Integer.class);
		
		//GET
		ResponseEntity<PersonDto> responseEntity = this.testRestTemplate.getForEntity("/parents/{id}", PersonDto.class, resultPersonId);
		PersonDto personResult = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(personResult.getFirstName()).isEqualTo("Jane");
		assertThat(personResult.getLastName()).isEqualTo("Doe");
	}
	
	@Test
	public void createPerson_thenChildren_thenFetchParent() throws ParseException {
		
		HttpEntity<CreatePersonDto> request = new HttpEntity<>(parentDto);
		Long resultPersonId = (Long) testRestTemplate.postForObject("/parents", request, Long.class);
		
		childDto.setParentId(resultPersonId);
		HttpEntity<CreatePersonDto> requestChild = new HttpEntity<>(childDto);
		Long resultChildId = (Long) testRestTemplate.postForObject("/parents", requestChild, Long.class);
		
		//GET
		ResponseEntity<PersonDto> responseEntity = this.testRestTemplate.getForEntity("/parents/{id}", PersonDto.class, resultPersonId);
		PersonDto personResult = responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(personResult.getFirstName()).isEqualTo("Jane");
		assertThat(personResult.getLastName()).isEqualTo("Doe");
		assertThat(personResult.getChildren().size()).isEqualTo(1);
		assertThat(personResult.getChildren().get(0).getFirstName()).isEqualTo("Janet");
	}
	
	
}
