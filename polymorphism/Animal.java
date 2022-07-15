package polymorphism;

public class Animal {
	public void walk() {
		System.out.println("Animal is walking");
	}
}
	class dog extends Animal{
		public void walk() {
			System.out.println("Dog is walking");
		}
		
		public void wagTail() {
			System.out.println("Dog is wagging its tail");
		}
	}
		class cat extends Animal {
			public void walk() {
				System.out.println("Cat is walking");
			}
			
			public void jump() {
				System.out.println("Cat is jumping");
		}
	}
