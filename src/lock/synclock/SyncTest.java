package lock.synclock;

/**
 * http://blog.csdn.net/xiaoyu714543065/article/details/8254603
 * @author Yu_Huo
 *
 */
public class SyncTest {
	
	private String name = "123";
	private String pass = "123";
	
	public synchronized void setData(String name , String pass){
		this.name = name ; 
		this.notify();
		try {
//			Thread.sleep(3000L);
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.pass = pass ; 
		System.err.println("name = "+name+" , pass = "+ pass);
	}
	
	
	/**
	 *  不添加 synchronized 的话  就不能保证 setData 方法的原子性
	 */
	public synchronized void getData(){
		System.out.println("name = "+name+" , pass = "+ pass);
		this.notify();
	}
	
	
	/**
	 * 使用wait()、notify()和notifyAll()时需要先对调用对象加锁
	 * 无锁状态调用 会抛出一下异常
	 * Exception in thread "main" java.lang.IllegalMonitorStateException
	   at java.lang.Object.wait(Native Method)
	   at java.lang.Object.wait(Object.java:503)
	   at lock.synclock.SyncTest.main(SyncTest.java:12)
	 */
	public void test(){
		SyncTest lock = new SyncTest();
		try {
//			synchronized(lock){
				lock.wait();
//			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		final SyncTest test = new SyncTest();
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				test.setData("456", "456");
			}
		});
		t.start();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test.getData();
	}
	
}
