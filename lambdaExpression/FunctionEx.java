package lambdaExpression;

import java.util.function.Function;

public class FunctionEx {
		public static void main(String[] args) {
			
			String s = "itorizon";
			Function<String, Integer> fn = str->str.length();
			int res = fn.apply(s);
			System.out.println("Length of the string :"+res);
			
			
		}
}
