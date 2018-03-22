package thread.lock.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *中断
 */
public class TryLockTest3 implements Runnable{
	public static final int MAX_RETRY = Runtime.getRuntime().availableProcessors() > 1 ? 64 : 1;
	private final ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			int retry = 0;
			while(!lock.tryLock()){
				if(retry++ > MAX_RETRY){
					lock.lock();
					System.err.println(Thread.currentThread().getName()+"阻塞式获取锁！");
					break;
				}
			}
			if(lock.isHeldByCurrentThread()){
				System.err.println(Thread.currentThread().getName()+ " 获得锁");
				Thread.sleep(3000l);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
				System.err.println(Thread.currentThread().getName()+ " 释放锁!");
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		TryLockTest3 test = new TryLockTest3();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("t1");
//		t1.setPriority(9);
		t2.setName("t2");
		
		t1.start();
		t2.start();
		
		
//		if(t1.isAlive()){
//			t1.interrupt();
//		}
		
	}
}
