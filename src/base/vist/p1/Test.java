package base.vist.p1;

import base.vist.p2.Son11;

public class Test {

	
	
	public static void main(String[] args) {
		Son1 s = new Son1();
		s.f();
//		s.clone();
		
		Son11 s11 = new Son11();
		s11.f();
//		s.clone();
		
		
		Test t = new Test();
		try {
			t.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
