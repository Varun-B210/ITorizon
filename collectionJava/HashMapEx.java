package collectionJava;

import java.util.HashMap;

public class HashMapEx {

	public static void main(String[] args) {

		HashMap<String,Integer> marks = new HashMap<>();
		marks.put("English", 85);
		marks.put("Hindi", 90);
		marks.put("Science", 90);
		marks.put("Maths", 95);
		marks.put("Computer",92);
		
		System.out.println(marks);
		System.out.println(marks.containsKey("Science"));
		System.out.println(marks.entrySet());
		System.out.println(marks.keySet());
		System.out.println(marks.values());
		marks.remove("Maths");
		System.out.println(marks);
		marks.replace("Hindi", 88);
		System.out.println(marks);
	}

}
