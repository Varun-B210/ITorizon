package com.inheritance;

public class Employee {
	final String name="varun";
	double salary;
	String organization;
	
	public Employee(String name, double salary, String organization) {
		
		this.salary=salary;
		this.organization=organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
	
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Employee :[name=" + name + ", salary=" + salary + ", organization=" + organization + "]";
	}

	
	public static void main(String[] args) {
		Employee emp = new Employee("",5000,"ITOrizon");
		System.out.println(emp.toString());
	}
}
