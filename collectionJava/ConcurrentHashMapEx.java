package collectionJava;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapEx extends Thread{

	static ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {

		ConcurrentHashMapEx chm = new ConcurrentHashMapEx();
		
		map.put(0, "Apple");
		map.put(1, "Orange");
		map.put(2, "Mango");
		map.put(3, "Grape");
		map.put(4, "Banana");
		chm.start();
		
		Iterator <Entry<Integer,String>> itr = map.entrySet().iterator();
		while(itr.hasNext()) {
			Entry<Integer, String> entry = itr.next();
			System.out.println("Key :"+entry.getKey()+" Value :"+entry.getValue());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void run() {
		try {
			Thread.sleep(500);
			map.put(5, "Papaya");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
