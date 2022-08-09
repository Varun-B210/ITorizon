package com.spring.jpa;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJPAApplication implements CommandLineRunner{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJpaRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJPAApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("\nPerson Details -> {}", repo.findByID(1002));
		logger.info("\nInsert new -> {}", repo.insert(new Person("Karan", "Manipal", Date.valueOf("2000-04-17"))));
		logger.info("\nDeleted Details -> {}", repo.deleteById(1004));
		logger.info("\nAll detaisl -> {}", repo.findAll());
	}

}
