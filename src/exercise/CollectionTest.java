package exercise;

import java.util.concurrent.ConcurrentHashMap;

public class CollectionTest {

	public static void main(String[] args) {
		concurrentHashMapTest();
	}

	public static void concurrentHashMapTest() {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
		// HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(4, 4);

		for (Integer inte : map.keySet()) {
			if (inte == 3) {
				 map.remove(inte);
				map.put(5, 5);
			}
			System.err.println(inte);
		}
		System.err.println(map.size());
	}
}
