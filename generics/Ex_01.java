package generics;

public class Ex_01 {
	public static void main(String[] args) {
		
		Data<String> d1 = new Data<>("hello");
		System.out.println(d1.toString());
		
		Data<Integer> d2 = new Data<>(10);
		System.out.println(d2.toString());
		
	}

}

class Data<T>{
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
	public Data(T data) {
		super();
		this.data = data;
	}

	
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}
	
}