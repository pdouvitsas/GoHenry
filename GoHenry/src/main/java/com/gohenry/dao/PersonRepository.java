package com.gohenry.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gohenry.entity.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer>{

}
