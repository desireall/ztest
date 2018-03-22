package exercise;

import org.junit.Test;

public class OperatorTest {

	@Test
	public void iselfIncreasing(){
		int i = 0;
		int temp = i++;
		System.err.println(temp);
		System.err.println(i);
	}

	@Test
	public void selfIncreasingi(){
		int i = 0;
		int temp = ++i;
		System.err.println(temp);
		System.err.println(i);
	}
	
	
	
}
