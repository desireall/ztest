package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberTest {
	
	
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	
    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";  
        return str.matches(reg);  
    }  
    
    public static boolean isNumeric2(String str){  
        for(int i=str.length();--i>=0;){  
           int chr=str.charAt(i);  
           System.out.println(chr);  
           if(chr<48 || chr>57) {  
             if(chr != 46)  
               return false;  
           }  
        }  
        return true;  
     } 
	
	
	public static void main(String[] args) {
		int a = 1;
		/**
		String aa ="1.121f";
		System.err.println(isNumeric2(aa));
		float a = 0.1f;
		System.err.println(Math.floor(a));
		System.err.println(Math.ceil(a));
		
		System.err.println(factorial(0));
		System.err.println(factorial(1));
		System.err.println(factorial(2));
		System.err.println(factorial(5));
		System.err.println(factorial(10));
		System.err.println(factorial(-10));
		 */
//		bathExp(24, 23);
//		getLength(5);
//		listTest();
	}
	
	
	
	public static void getLength(int n){
		System.err.println(Math.sqrt(2 * n * n));
	}
	

	public static void bathExp(int level , int sec){
		System.err.println((int)((0.33f * level + 4.67f) * sec));
	}
	
	
	
	public static void test(double num , int count , int time){
		num *= 100;
		double  temp = num / time * count + num;
		temp -= (num * 0.06);
//		temp -= 11501;
		System.err.println(temp);
		System.err.println(num  - 11501);
	}

	
	public static void pay(double num){
		double total = 0;
		double dele = 3.0 / 100;
	}
	
	
	public static int factorial(int n){
		if(n < 0) return 0;
		if(n == 0) return 1;
		return n * factorial(n-1);
	}
	
	
	public static void listTest(){
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
//		a = a.subList(2, a.size());
		a = a.subList(0, 2);
	     a.subList(2,a.size()).clear();;
		System.err.println(Arrays.toString(a.toArray()));
	}
	
}
