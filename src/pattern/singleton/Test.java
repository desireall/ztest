package pattern.singleton;

public class Test extends Thread {

	@Override
	public void run() {
//		System.err.println(HungrySingleton.getInstance().hashCode());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(LazySingleton.getInstance().hashCode());
//		System.err.println(LazySingletonImprove.getInstance().hashCode());
	}

	public static void main(String[] args) {
		Test[]  myThread = new Test[5];
		for (int i = 0; i < myThread.length; i++) {
			myThread[i] = new Test();
		}
		
		for (int i = 0; i < myThread.length; i++) {
			myThread[i].start();
		}
		
	}

}
