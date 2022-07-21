package exercises;

public class A {
	public String str;
	public double val;
	
	public A() {
	this.str="Hello";
	this.val=56.214;
	}
	public A(String st) {
		this.str=st;
	}
	
	public A(double value) {
		this.val=value;
	}
	
	public A(String s, double v) {
		this.str=s;
		this.val=v;
	}
	public void print() {
	
		System.out.println("str :"+str+", val :"+val);
}
}
