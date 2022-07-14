package com;

public class StringTest {
	public static void main(String[] args) {
		String str = "  \nCore Java  \t";
		System.out.println("given string :"+str);
		
		String trim = str.trim();
		System.out.println(trim);
		
		String uc = trim.toUpperCase();
		System.out.println(uc);
	
		char ch = uc.charAt(2);
		System.out.println(ch);
		
		char ch1 = str.trim().toUpperCase().charAt(2);//method chaining
		System.out.println(ch1);
	}

}
