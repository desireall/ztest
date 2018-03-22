package collections.map;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailSafeTest {

	public static void singleThreadFailSafeTest(){
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			System.err.println(map.get(iterator.next()));
			map.put("4", "4");
		}
	}
	
	
	public static void mutlThreadFailSafeTest(){
		final List<Integer> list = new CopyOnWriteArrayList<Integer>(); 
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (Integer integer : list) {
					System.err.println(integer);
					if(integer == 3){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 9; i > 0 ; i--) {
					list.remove(i);
					System.err.println("remove  "+ i);
				}
			}
		});
		
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args) {
		mutlThreadFailSafeTest();
	}
}
