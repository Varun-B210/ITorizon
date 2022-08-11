package com.spring.data.jpa.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserRepoCLR implements CommandLineRunner{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	UserRepository userrepo;
	
	@Override
	public void run(String... args) throws Exception {
		//Person person = new Person();
		//logger.info("\nDetails added ->{}",userrepo.save(person));
		
		//userrepo.deleteAll();
		logger.info("\n All Details ->{}",userrepo.findAll());
		//logger.info("\n Inserted ->{}", userrepo.save(person));
		logger.info("\n No of entries ->{}",userrepo.count());
		userrepo.deleteAll();
	}
}
