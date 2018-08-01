# GoHenry
GoHenry

# Language used
Java 8

# Framework
Spring boot 1.5.3.RELEASE

# POST requests (save)
To create a person a sample JSON is given below
  {
     "title":"Mrs",
     "firstName":"Jane",
     "lastName":"Doe",
     "emailAddress":"jane.doe@gohenry.co.uk",
     "dateOfBirth":"1990-06-03",
     "gender":"female",
     "secondName":""
  }

To create a child of this person, a sample JSON is given below  
{  
     "firstName":"Janet",
     "lastName":"Doe",
     "emailAddress":"janet.doe@gohenry.co.uk",
     "dateOfBirth":"2010-05-22",
     "gender":"female",
     "secondName":"",
     "parentId":1
}

The parentId is the id of the parent.

# Tests
There are unit tests and integration tests.

# Built tool
Maven

# Future work (if more than 3 hours is spent)
add proper validation

make the update operations

in the unit tests include more fields to test

proper encapsulation in the DTO classes
