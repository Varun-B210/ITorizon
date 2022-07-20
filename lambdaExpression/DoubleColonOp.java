package lambdaExpression;

public class DoubleColonOp {
	public static void main(String[] args) {
		
		MyInterfaceEx mi = ()->System.out.println("Hello");
		mi.method1();
		
	}
	
	public static void method2() {
		System.out.println("Hello java world");
		
	}
	
	public void method3() {
		System.out.println("Hello DoubleColonOperator");
		
	}

}

interface MyInterfaceEx{
	public void method1();
}

class CallerClass{
	public static void main(String[] args) {
		
		DoubleColonOp obj = new DoubleColonOp();
		MyInterfaceEx mie1 = obj :: method3;
		mie1.method1();
	
		
		MyInterfaceEx mie = DoubleColonOp :: method2;
	mie.method1();
	}
}
