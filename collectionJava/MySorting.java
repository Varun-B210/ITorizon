package collectionJava;

import java.util.Comparator;

public class MySorting implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		Integer a = (Integer)o1;
		Integer b = (Integer)o2;
		
		if(a<b)
			return +1;
		if(a>b)
			return -1;
		else
		return 0;
	}

}
