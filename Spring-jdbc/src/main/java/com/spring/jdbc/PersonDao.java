package com.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
	
	@Autowired
	JdbcTemplate jdbctemplate;
	
	class PersonMapper implements RowMapper<Person>{
		
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthday(rs.getDate("birthday"));
			
			return person;
		}

	}
	
	public List<Person> personDetails() {
		return jdbctemplate.query("select * from person", new PersonMapper());

	}

	public Person findByID(int id) {

		return jdbctemplate.queryForObject("select * from person where id =?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	public int deleteByID(int id) {

		return jdbctemplate.update("delete from person where id =?", new Object[] { id });
	}

	public int insert(Person person) {

		return jdbctemplate.update("insert into person (id, name, location, birthday) values (?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(), person.getBirthday() });
	}
	
	public int update(Person person) {

		return jdbctemplate.update("update person set name=?, location=?, birthday=? where id=?",
				new Object[] { person.getName(), person.getLocation(), person.getBirthday(), person.getId()});
	}

	
}
