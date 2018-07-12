package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinAction {

	public static void forkTest() throws InterruptedException {
		ForkJoinPool forkPool = new ForkJoinPool();
		ForkAction action = new ForkAction(0, 300);
		forkPool.submit(action);
		forkPool.awaitTermination(2, TimeUnit.SECONDS);
		forkPool.shutdown();
	}

	public static void printCount() {
		long start = System.currentTimeMillis();

		for (int i = 1; i <= 300; i++) {
			System.out.println("print num: " + i);
		}

		System.err.println("cost time " + (System.currentTimeMillis() - start) + " ms");
		
		System.err.println(Runtime.getRuntime().availableProcessors());
	}
	
	public static void main(String[] args) throws Exception {
//		printCount();
		forkTest();
	}
	
}

class ForkAction extends RecursiveAction {
	private static int ACTION_COUNT = 50;

	private int start;
	private int end;

	public ForkAction(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {

		if (end - start < ACTION_COUNT) {
			for (int i = start; i <= end; i++) {
				System.out.println(Thread.currentThread().getName() + "print num: " + i);
			}
		} else {

			int modle = (end - start) / 2;
			ForkAction startFork = new ForkAction(start, start + modle);
			ForkAction endFork = new ForkAction(end - modle, end);
			startFork.fork();
			endFork.fork();
		}

	}

}
