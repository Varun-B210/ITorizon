package lambdaExpression;

public class LambdaEx_01 {
	public static void main(String[] args) {
		function func = ()->System.out.print("Lambda Expression");
		func.method();
		
	}
}
	interface function{
		public void method();
	}
	
	/*
	 * class callFunc implements function{
	 * 
	 * @Override public void method() { System.out.println("Lambda expression"); }
	 * 
	 * }
	 */


