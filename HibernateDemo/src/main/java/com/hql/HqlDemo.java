package com.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class HqlDemo {
	public static void main(String[] args) {
		Student student = new Student();
		Laptop laptop = new Laptop();
		
//		student.setRollno(101);
//		student.setName("dhanush");
//		student.setMarks(70);
		student.setLaptop(laptop);
		
		
//		laptop.setId(1);
//		laptop.setBrand("HP");
//		laptop.setPrice(50000);
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Student.class)
				.addAnnotatedClass(Laptop.class);
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
//		Query q = session.createQuery("from Student");
//		System.out.println(q.list());
		
		session.save(laptop);
		session.save(student);
		session.getTransaction().commit();

	}
}
