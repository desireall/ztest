package exercise;

public class TryCatchTest {

	
	
	
	public static int test(){
		int a = 10;
		try {
			a = 10 / 0;
		} catch (Exception e) {
			a = 30;
			return a;
		}finally{
			a = 40;
		}
		return a;
	}
	
	
	public static void main(String[] args) {
		System.err.println(test());
	}
	
}
