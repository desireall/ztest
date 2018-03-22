package nio.normal.blocking;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

public class BlockingTest {

	
	@Test
	public void test() throws IOException, InterruptedException{
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					BlockingServer.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		Thread.sleep(1000l);
		
		final char ops[] = {'+','-','*','/'};
		
		final Random random = new Random(System.currentTimeMillis());
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					String expression = random.nextInt(10)+ " "+ ops[random.nextInt(4)] + " "+ (random.nextInt(10) + 1);
					BlockingClient.send(expression);
					try {
						Thread.currentThread().sleep(3000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
	}
}
