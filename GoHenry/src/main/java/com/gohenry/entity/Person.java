package com.gohenry.entity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gohenry.utils.Utils;

@Entity
@Table(name = "person")
public class Person {
	
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "title", unique = false, nullable = true, length = 64)
	private String title;
	
	@Column(name = "first_name", unique = false, nullable = false, length = 128)
	private String firstName;
	
	@Column(name = "last_name", unique = false, nullable = false, length = 128)
	private String lastName;
	
	@Column(name = "email_address", unique = false, nullable = true, length = 128)
	private String emailAddress;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_birth", length = 19, nullable = false)
	private Date dateOfBirth;
	
	@Column(name = "gender", unique = false, nullable = false, length = 6)
	private String gender;
	
	@Column(name = "second_name", unique = false, nullable = true, length = 128)
	private String secondName;
	
	@ManyToOne
	@JoinColumn(name = "parent_id", nullable = true)
	private Person parent;

	@OneToMany(mappedBy = "parent")
	private List<Person> children;
	
	public Person(){
		
	}	
	
	public Person(final Long id, final String title, final String firstName, final String lastName, final String emailAddress, final String dateOfBirth,
			final String gender, final String secondName, final Person parent, final List<Person> children) throws ParseException {
		super();
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.dateOfBirth = Utils.convertStringToDate(dateOfBirth);
		this.gender = gender;
		this.secondName = secondName;
		this.parent = parent;
		this.children = children;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

//	public Person getParent() {
//		return parent;
//	}
//
//	public void setParent(Person parent) {
//		this.parent = parent;
//	}

	
	
	public List<Person> getChildren() {
		return children;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	

	public Person getParent() {
		return parent;
	}

	public void setParent(Person parent) {
		this.parent = parent;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}
	
	
	
}
