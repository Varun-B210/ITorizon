package com.spring.data.jpa.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Person, Integer>{

}
