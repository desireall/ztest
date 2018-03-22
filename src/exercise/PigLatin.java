package exercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatin {
	/**
	Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of the word.
	Rule 2: If a word begins with a consonant sound, move it to the end of the word
	        , and then add an "ay" sound to the end of the word.
    */
	public static String translateWord(String str) {
		 Pattern p = Pattern.compile("[aeiou]");
		 Matcher m = p.matcher(str);
		 
         if(m.find()){
        	 /**
        	  * find(int start)重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。 
        	  * 如果匹配成功，则可通过 start、end 和 group 方法获取更多信息，而 find() 方法的后续调用将从此匹配操作未匹配的第一个字符开始。
        	  * 
        	  */
//        	 int index = str.indexOf(m.group());
        	 int index = m.start();
        	 if(index == 0){
        		 return str+"ay";
        	 }else{
        		 return str.substring(index)+str.substring(0, index)+"ay";
        	 }
         }
         return str;
	}
	
	public static String translate(String str) {
		String[] temp = str.split(" ");
		StringBuilder a = new StringBuilder();
		for (int i = 0; i < temp.length; i++) {
			a.append(translateWord(temp[i]));
			if (i != temp.length - 1) {
				a.append(" ");
			}
		}
		return a.toString();
	}
	
	
	public static void main(String[] args) {
		System.err.println(translate("apple"));
		System.err.println(translate("banana"));
		System.err.println(translate("cherry"));
		System.err.println(translate("eat pie"));
		System.err.println(translate("three"));
		
//		translateWord("cherry");
	
	}
	
	
}
