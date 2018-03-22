package thread.lock.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 定时锁
 */
public class TryLockTest implements Runnable{
	private final ReentrantLock lock = new ReentrantLock();

	public ReentrantLock getLock() {
		return lock;
	}



	@Override
	public void run() {
		try {
			if(lock.tryLock(2, TimeUnit.SECONDS)){
				try {
					System.err.println(Thread.currentThread().getName()+ " 获得锁");
					Thread.sleep(4000l);
				} finally {
					lock.unlock();
					System.err.println(Thread.currentThread().getName()+ " 释放锁!");
				}
			}else{
				System.err.println(Thread.currentThread().getName()+ " 未获得锁");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(Thread.currentThread().getName()+ " 中断！");
		}
	}
	
	
	
	public static void main(String[] args) {
		TryLockTest test = new TryLockTest();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("t1");
		t2.setName("t2");
		
		t1.start();
		t2.start();
		
		if(t1.isAlive()){
			t1.interrupt();
		}
	}
}
