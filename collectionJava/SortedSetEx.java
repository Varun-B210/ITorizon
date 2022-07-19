package collectionJava;

import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetEx {
	public static void main(String[] args) {
		
	SortedSet s = new TreeSet();
	
	s.add(12);
	s.add(4);
	s.add(6);
	s.add(1);
	s.add(8);
	
	System.out.println(s);
	System.out.println(s.tailSet(6));
	System.out.println(s.headSet(8));
	System.out.println(s.subSet(4, 12));
	System.out.println(s.contains(1));

}
}