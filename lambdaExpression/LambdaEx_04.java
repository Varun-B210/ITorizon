package lambdaExpression;

public class LambdaEx_04 {

	public static void main(String[] args) {
		MyInterface02 intf02 = a ->{
			int x = a+20;
			x=x/2;
			
			System.out.println(x);
			return x;
		};
		intf02.method02(50);
		
	}

}

interface MyInterface02{
	public int method02(int a);
}