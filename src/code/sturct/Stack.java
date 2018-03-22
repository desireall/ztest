package code.sturct;

public class Stack {
	
	Object[] temp;
	
	int top = -1;

	public Stack(Object[] temp, int top) {
		super();
		this.temp = temp;
		this.top = top;
	}

	public Stack(int size) {
		super();
		this.temp = new Object[size];
	}
	
	public Object push(Object e){
		temp[++top] = e;
		return e;
	}
	
	public Object pop(){
		if(top == -1){
			return null;
		}
		return temp[top--];
	}
	
	public static void main(String[] args) {
//		char[] str = "i love you".toCharArray();
//		Stack all = new Stack(str.length);
//		for (int i = 0; i < str.length; i++) {
//			all.push(str[i]);
//		}
//		Object tt = null;
//		Stack other = new Stack(str.length);
//		
//		char[] aa = new char[str.length];
//		
//		int i = 0;
//		Object ot = null;
//		do{
//			tt = all.pop();
//			if(' '== (Character)tt){
//				do{
//					ot = other.pop();
//					aa[i++] = (Character)ot;
//				}while(ot == null);
//				aa[i++] = ' ';
//			}else{
//				other.push(tt);
//			}
//		}while(tt != null);
//		
//		System.err.println(new String(aa));
		
//		char[] s = "i love you".toCharArray();
		System.err.println(reverse("i love you"));
	    
	}
	
	
	
	public static String reverse(String s){  
	    int pos=0;  
	    StringBuilder sb=new StringBuilder();  
	    for(int i=0;i<s.length();i++){  
	        char c=s.charAt(i);  
	        if(c==' '){  
	            pos=0;  
	        }  
	        sb.insert(pos,c); 
//	        System.err.println(sb.toString());
	        if(c!=' '){  
	            pos++;  
	        }  
	    }  
	    return sb.toString();  
	          
	}  
}
