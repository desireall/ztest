package thread.colleaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

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

	public static void synchronousQueueTest() {
			// 如果为 true，则等待线程以 FIFO 的顺序竞争访问；否则顺序是未指定的。
			// SynchronousQueue<Integer> sc =new SynchronousQueue<>(true);//fair
			SynchronousQueue<Integer> sc = new SynchronousQueue<>(); // 默认不指定的话是false，不公平的
			new Thread(() -> { //生产者线程，使用的是lambda写法，需要使用JDK1.8
				while (true) {
					try {
						sc.put(new Random().nextInt(50));
						//将指定元素添加到此队列，如有必要则等待另一个线程接收它。
						// System.out.println("sc.offer(new Random().nextInt(50)): "+sc.offer(new Random().nextInt(50))); 
						// 如果另一个线程正在等待以便接收指定元素，则将指定元素插入到此队列。如果没有等待接受数据的线程则直接返回false
						// System.out.println("sc.offer(2,5,TimeUnit.SECONDS):
						// "+sc.offer(2,5,TimeUnit.SECONDS));//如果没有等待的线程，则等待指定的时间。在等待时间还没有接受数据的线程的话，直接返回false
						System.out.println("添加操作运行完毕...");//是操作完毕，并不是添加或获取元素成功!
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(() -> {//消费者线程。使用的是lambda创建的线程写法需要使用jdk1.8
				while (true) {
					try {
						System.out.println("-----------------> sc.take: " + sc.take());
						System.out.println("-----------------> 获取操作运行完毕...");//是操作完毕，并不是添加或获取元素成功!
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();

	}
	
	
	public static void main(String[] args) {

//		concurrentHashMapTest();
		
		synchronousQueueTest();

	}
	
	
	
}
