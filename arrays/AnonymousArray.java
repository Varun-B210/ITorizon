package arrays;

public class AnonymousArray {
	public static void main(String[] args) {
		
		sum(new int[] {10,20,30});
	}

	private static void sum(int[] arr) {
		int sum = 0;
		for(int array: arr) {
			sum = sum + array;
		}
		System.out.println(sum);
	}
}
