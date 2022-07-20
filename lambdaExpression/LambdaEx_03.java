package lambdaExpression;

public class LambdaEx_03 {
	public static void main(String[] args) {
		MyInterface01 intf = s ->{
			System.out.println("Length of the String is :"+s.length());
			return s.length();
		};
	intf.method01("Lambda");
		
		
	}

}

interface MyInterface01{
	public int method01(String s);
}