package test;

import java.util.UUID;

public class Test {
	
	public static void check(int x , int y, int z){
		float addvalue = 1.3f; 
		int a = ((int)(x * addvalue)) * z / 1000;
		int b = ((int)(y * addvalue)) * z / 1000;
		int add = ((int)(2 * x - y * addvalue)) * z / 1000;
		int changeA = add - a;
		int changeB = a - b;
		if(changeA != changeB && (changeA - changeB > 1)){
			System.err.println(String.format("原值: %s ,差值  %s,系数: %s 变更值：%s", x, (x-y),z ,(changeA - changeB)));
		}
	}
	

	public static void main(String[] args) {
//		for (int i = 100000; i < 130000; i++) {
//			for (int j = 4000; j <6000; j++) {
//				check((i+j),i,666);
//			}
//		}

		for (int i = 0; i < 10; i++) {
//			String sn = UUID.randomUUID().toString().replace("-", "");
			String sn = UUID.randomUUID().toString();
			System.err.println(sn+"            "+sn.length());
		}
	}
}
