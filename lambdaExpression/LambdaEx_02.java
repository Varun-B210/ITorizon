package lambdaExpression;

public class LambdaEx_02 {
	public static void main(String[] args) {
		MyInterface intf = (a, b)->System.out.print("Sum :"+a+"+"+b+"="+(a+b));
		intf.method(20, 30);
		
	}

}
interface MyInterface{
	public void method(int a, int b);
}