package com.spring.jdbc;

import java.sql.Date;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("\nAll persons -> {}", dao.personDetails());
		logger.info("\nID = 1001 -> {}", dao.findByID(1001));
		logger.info("\nDeleting 1002 ->No of rows deleted - {}", dao.deleteByID(1002));
		logger.info("\nInserting values -> {}", dao.insert(new Person(1005, "Kumar", "Bangalore", Date.valueOf("1996-11-05")) ));
		logger.info("\nUpdating values -> {}", dao.update(new Person(1005, "Akshay", "Bangalore", Date.valueOf("1996-11-01")) ));

	}

}
