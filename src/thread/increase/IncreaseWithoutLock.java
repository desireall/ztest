package thread.increase;

public class IncreaseWithoutLock {

	public static  int count = 0;
//	public static volatile AtomicInteger count = new AtomicInteger(0);
	
	
	public static void increase(){
//		try {
//			Thread.sleep(1);
//		} catch (Exception e) {
//		}
		count++;
	}
	
	public static void autoIncrease(){
//		count.incrementAndGet();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					IncreaseWithoutLock.increase();
//					IncreaseWithoutLock.autoIncrease();
				}
			}).start();
		}
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("Result: IncreaseWithoutLock.count=" + IncreaseWithoutLock.count);  
	}
}
