package com;

import java.util.Scanner;

public class ScannerEx {
	public static void main(String[] args) {
		while(true) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Y or N");
		
		String a = sc.next();
		
		if(a.equalsIgnoreCase("Y")) {
			System.out.println("Welcome!");
			break;
		}
		else if(a.equalsIgnoreCase("N")) {
			System.out.println("Good Bye!");
			break;
		}
		else if(a.length()>1){
			System.out.println("Please enter a single character\n");
			continue;
		}
		else {
			System.out.println("Please enter the correct character");
			continue;
		}
		
	}
	}
}

