package lambdaExpression;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class CollectionSortLambda {
	public static void main(String[] args) {
		
		List<Integer> ls = new ArrayList<Integer>();
		ls.add(10);
		ls.add(13);
		ls.add(2);
		ls.add(6);
		ls.add(15);
		ls.add(9);
		
		
		Collections.sort(ls,(o1,o2)->{
			if(o1>o2)
				return -1;
			else if(o1<o2)
				return +1;
			else
				return 0;
		}) ;
		
		System.out.println(ls);
		
	}

}
