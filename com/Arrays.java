package com;

public class Arrays {
	public static void main(String[] args) {
		int sum=0;
		int[] arr = new int[5];
		arr[0]=10;
		arr[1]=20;
		arr[2]=30;
		arr[3]=40;
		arr[4]=50;
		for(int i=0;i<arr.length;i++) {
			sum=sum+arr[i];
		}
		System.out.println("Sum of the array elements is "+sum);
		System.out.println("Average of the array elements is "+sum/arr.length);
	}

}
