package lambdaExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsEx {
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(30);
		list.add(20);
		list.add(50);
		list.add(40);
		list.add(10);
		
		System.out.println(list);
		
		List<Integer> ls = list.stream().map(i->i*2).collect(Collectors.toList());
		System.out.println(ls);
		
		List<Integer> ls1 = list.stream().sorted().collect(Collectors.toList());
		System.out.println(ls1);
		
		List<Integer> ls2 = list.stream().sorted((i1,i2) -> i2.compareTo(i1)).collect(Collectors.toList());
		System.out.println(ls2);
	}
}
