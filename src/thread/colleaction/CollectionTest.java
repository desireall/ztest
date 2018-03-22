package thread.colleaction;

import java.util.HashMap;
import java.util.Map;

public class CollectionTest {

	public static void concurrentHashMapTest() {
//		Map<Object, Object> map = new ConcurrentHashMap<>();
		Map<Object, Object> map = new HashMap<>();
        try {
//        	map.put(1, null);
        	map.put(null, null);
		} catch (Exception e) {
			System.err.println(e);
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {

		concurrentHashMapTest();

	}
}
