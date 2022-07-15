package polymorphism;

public class Circus {
	
	public static void doTrick(Animal animal) {
	
		animal.walk();
		if(animal instanceof dog) {
			dog d = (dog)animal;
			d.wagTail();	
	}
		if(animal instanceof cat) {
			cat c = (cat) animal;
			c.jump();
		}
	}
	
	public static void main(String[] args) {
		
		Animal a1 = new Animal();
		Circus.doTrick(a1);
		
		Animal a2 = new dog();
		Circus.doTrick(a2);
		
		Animal a3 = new cat();
		Circus.doTrick(a3);
	}
}
