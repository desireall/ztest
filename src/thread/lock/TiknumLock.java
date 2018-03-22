package thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 排队的 自旋锁
 */
public class TiknumLock {

	private static final AtomicInteger serverNum = new AtomicInteger(); // 服务号

	private static final AtomicInteger tikNum = new AtomicInteger(); // 排队号
	
	public int lock(){
		int ticknum = tikNum.getAndIncrement();
		while(serverNum.get() != ticknum){
			
		}
		return ticknum;
	}
	
	public void unlock(int tickNum){
		int next = tickNum + 1;
		tikNum.compareAndSet(tickNum, next);
	}
	
}
