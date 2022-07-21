package exercises;

public class ATest {
	public static void main(String[] args) {
		A a1 = new A();
		a1.print();
		
		A a2 = new A("test");
		a2.print();
		
		A a3 = new A(9876.00546);
		a3.print();
		
		A a4 = new A("test",9.5);
		a4.print();
	}
}
