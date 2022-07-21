package com;

public class SwitchCase {
	public static void main(String[] args) {
		int num = 11;
		num=num%2;
		switch(num) {
		case 0:System.out.println("Even");
						break;
		default:System.out.println("Odd");
				break;
		}
	}

}
