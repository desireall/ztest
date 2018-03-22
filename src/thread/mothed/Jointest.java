package thread.mothed;

public class Jointest {

	public static class MyThread extends Thread {
		@Override
		public void run() {
			System.err.println("over");
		}
	}

	public static void main(String[] args) {
		final Thread t1 = new MyThread();

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.err.println("1");
				synchronized (t1) {
					try {
						t1.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.err.println("2");
			}

		});

		t2.start();
		t1.start();
		
	}

}
