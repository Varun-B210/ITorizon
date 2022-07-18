package collectionJava;

import java.util.LinkedList;

public class LinkedListEx {
public static void main(String[] args) {
	
	LinkedList ls = new LinkedList();
	
	ls.add("Hello");
	ls.add(50);
	ls.add(null);
	ls.add(false);
	
	System.out.println(ls);
	ls.addFirst('d');
	System.out.println(ls);
	System.out.println(ls.get(2));
	ls.set(1, "Hi");
	System.out.println(ls);
	ls.add(0, "Bangalore");
	System.out.println(ls);
	ls.remove(1);
	System.out.println(ls);
	System.out.println();
}
}
