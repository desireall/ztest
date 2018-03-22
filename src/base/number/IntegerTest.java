package base.number;

public class IntegerTest {

	public static void main(String[] args) {
		Integer a = 4;
		Integer b = 4;
		
		System.out.println(a == b);  // ---- true    IntegerCache
		
		
		Integer c = 400;
		Integer d = 400;
		System.out.println(c == d); // ---- false   
	}
}
