package com.spring.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonJpaRepo {
	
	@PersistenceContext
	EntityManager manager;//connect to DB
	
	public List<Person> findAll(){
		TypedQuery<Person> query = manager.createNamedQuery("findAllDetails", Person.class);
		return query.getResultList();
	}
	
	public Person findByID(int id) {
		return manager.find(Person.class, id);
	}
	
	public Person insert(Person person) {
		return manager.merge(person);
	}
	
	public Person update(Person person) {
		return manager.merge(person);
	}
	
	public int deleteById(int id) {
		Person person = findByID(id);
		manager.remove(person);
		return person.getId();
	}
}
