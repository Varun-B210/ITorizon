package collectionJava;

import java.util.TreeSet;

public class ComparatorEx {

	public static void main(String[] args) {
		TreeSet<Integer> ts = new TreeSet<>();
		ts.add(13);
		ts.add(5);
		ts.add(15);
		ts.add(10);
		ts.add(8);
		
		System.out.println("Using Comparable :"+ts);
		TreeSet<Integer> t = new TreeSet<>(new MySorting());

		t.add(13);
		t.add(5);
		t.add(15);
		t.add(10);
		t.add(8);
		
		System.out.println("Using Comparator :"+t);
	}
}	