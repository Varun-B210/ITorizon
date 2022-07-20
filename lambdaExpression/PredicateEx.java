package lambdaExpression;

import java.util.function.Predicate;

public class PredicateEx {
	public static void main(String[] args) {
		int salary = 6000;
		Predicate<Integer> p = s-> s>=5000;
		boolean res = p.test(salary);
		System.out.println(res);
		
	} 

}
