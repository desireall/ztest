package thread.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class Cache {

	private DelayQueue<CacheObject> queue = new DelayQueue<CacheObject>();
	
//	private ConcurrentHashMap<Integer , CacheObject>  data = new ConcurrentHashMap<Integer , CacheObject>();
//	
//	private static final ReentrantLock lock = new ReentrantLock(); 
//	
//	
//	public CacheObject put(Integer key, CacheObject obj){
//		try {
//			lock.lock();
//			CacheObject oldObj = data.get(key);
//			if(oldObj == null){
//				data.put(key, obj);
//				queue.offer(obj);
//			}else{
//				oldObj.update(System.currentTimeMillis() + 200);
//			}
//		} finally {
//			lock.unlock();
//			return obj;
//		}
//	}
	
	public void put(CacheObject obj){
		queue.offer(obj);
	}
	
	public void remove(){
		while(!queue.isEmpty()){
			CacheObject oldObj = null;
			try {
				oldObj = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(oldObj != null){
				System.err.println(" remove  "+oldObj.toString());
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		final long time = System.currentTimeMillis();
		System.err.println(time);
		final CacheObject obj1 = new CacheObject(1 , 10000);
		CacheObject obj2 = new CacheObject(2 ,  time + 3000);
		final Cache cache = new Cache();
		cache.put(obj1);
		cache.put(obj2);
		cache.put(new CacheObject(3 , time + 5000));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				obj1.update(time + 7000);
			}
		});
		
		a.start();

		Thread b = new Thread(new Runnable() {
			
			@Override
			public void run() {
				cache.remove();
			}
		});
		
		b.start();
		
	}
	
}
