package exercises;

import java.util.Arrays;

public class ArraySort {
	
	public void sorting(int[] arr) {
		Arrays.sort(arr);
		System.out.println("Sorted array is :"+ Arrays.toString(arr));
	}

	public static void main(String[] args) {
		ArraySort as = new ArraySort();
		int[] array = {8,1000,-10,76,276};
		as.sorting(array);
	}
	
}
