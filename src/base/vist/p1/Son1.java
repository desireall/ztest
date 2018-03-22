package base.vist.p1;

public class Son1 extends Father{

	static{
		System.out.println("son1 init!");
	}
	
	public static void main(String[] args) {
		int k = 0 ;
		System.out.println(++k);
	}
}
