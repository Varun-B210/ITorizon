package com.hibernate.demo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmpDetails details = new EmpDetails();
//        Name name = new Name();
//        name.setFname("Dhanush");
//        name.setLname("P");
//        name.setMname("Naik");
//        
//        details.setName(name);
//        details.setLocation("Bangalore");
//        details.setSalary(30000);
        
        
        Configuration config = new Configuration().configure().addAnnotatedClass(EmpDetails.class);
        SessionFactory factory = config.buildSessionFactory();
        Session session1 = factory.openSession();
        Transaction txn = session1.beginTransaction();
        details=session1.get(EmpDetails.class, 3);
        txn.commit();
        System.out.println(details);
        
        Session session2 = factory.openSession();
        txn = session2.beginTransaction();
        details=session2.get(EmpDetails.class, 2);
        txn.commit();
        System.out.println(details);
        
    }
}
