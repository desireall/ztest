package base.current.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	public static void main(String[] args) {
	
		AtomicInteger num = new AtomicInteger(Integer.MAX_VALUE);
		
		System.err.println(num.incrementAndGet());
	}
}
