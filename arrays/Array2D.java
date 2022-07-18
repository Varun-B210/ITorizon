package arrays;

public class Array2D {
	public static void main(String[] args) {
		
	int[][] arr = {{10,20,30},{40,50}};
	
	for(int[] array: arr) {
		for(int ele:array) {
			System.out.println(ele);
		}
	}

	int[] x = {'a','z','A','Z'};
	for(int xarr:x) {
		System.out.println(xarr);
	}
}
}