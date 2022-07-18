package collectionJava;
import java.util.ArrayList;

public class ArrayListEx {
	public static void main(String[] args) {
		
	ArrayList<Comparable> al = new ArrayList<Comparable>();
	al.add("Hello");
	al.add(25);
	al.add('b');
	al.add(null);
	al.add(true);
	
	System.out.println(al);
	al.remove(3);
	System.out.println(al);
	al.remove(new Integer(25));
	System.out.println(al);
	System.out.println(al.get(0));
	al.set(1, 'x');
	System.out.println(al);
}
}