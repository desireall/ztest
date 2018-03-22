package base.string;

import java.util.Arrays;

public class StringTest {
	public static void main(String[] args) {
		String test = "test";
		
		StringBuilder builder = new StringBuilder("123456789");
		
//		System.out.println(builder.delete(0, 5));
		System.out.println(builder.insert(3, 5));
//		test.getChars(srcBegin, srcEnd, dst, dstBegin);
		char[] value = new char[10];
		for (int i = 0; i < value.length; i++) {
			value[i] = (char)((i+1));
		}
		
		System.arraycopy(value, 5, value, 0, 4);
		
		System.out.println(value.length);
		System.out.println(Arrays.toString(value));
	}
}
