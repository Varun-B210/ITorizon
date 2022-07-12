package com;

/**
 * 
 * @author Varun_B
 *
 */
public class Objects {
	public String name;
	public int age;
	public String city;
	
	public void drive() {
		System.out.println(name +" is driving");
	}
	public void run() {
		System.out.println(name +" is running");
	}
	
	public static void main(String[] args) {
		Objects obj = new Objects();
		obj.name="varun";
		obj.drive();
	}

}
