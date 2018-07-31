package com.gohenry.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.gohenry.entity.Person;
import com.gohenry.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Test
	public void saveNewPerson_WithChildren_ReturnsPersonWithChildren() throws Exception {
		Person child = new Person(new Long(2), null, "Janet", "Doe", "janet.doe@gohenry.co.uk", 
				"2010-05-22", "female", "", null, null);
		
		Person person = new Person(new Long(1), "Mrs", "Jane", "Doe", "jane.doe@gohenry.co.uk", 
				"1990-06-03", "female", "", null,
				Arrays.asList(child));
		
		when(this.personService.saveOrUpdate(person)).thenReturn(person);
		 this.mockMvc.perform(post("/parents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(person)))
                .andExpect(status().isOk());
	}
	
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
