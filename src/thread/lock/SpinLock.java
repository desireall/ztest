package thread.lock;

import java.util.concurrent.atomic.AtomicReference;

import sun.misc.Unsafe;

/**
 * 自旋锁
 * 
 * https://coderbee.net/index.php/concurrent/20131115/577
 *
 */
public class SpinLock {
	
	private AtomicReference<Thread> sign = new AtomicReference<Thread>();
	
	public void lock(){
		Thread thread = Thread.currentThread();
		while(!sign.compareAndSet(null, thread)){
		}
	}
	
	public void unlock(){
		Thread thread = Thread.currentThread();
		sign.compareAndSet(thread , null);
	}
	
	
	public static void main(String[] args) {
		 
	     
	}
}
