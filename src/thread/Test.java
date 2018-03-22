package thread;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		
		final Test obj = new Test();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				
				synchronized (obj) {
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.err.println("wait");
				}
				
			}
			
		});
		t1.start();
		
		
		Thread.sleep(1000);
		
		Thread t2 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				
				synchronized (obj) {
//					obj.notifyAll();
					obj.notify();
					System.err.println("wake up");
				}
				
			}
			
		});
		t2.start();
//		t1.interrupt();
	}
}
