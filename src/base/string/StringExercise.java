package base.string;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import sun.misc.BASE64Decoder;

public class StringExercise {

	public static void main(String[] args) {
		// titleize("jaws").should == "Jaws"
		// titleize("david copperfield").should == "David Copperfield"
		// titleize("war and peace").should == "War and Peace"
		// titleize("the bridge over the river kwai").should ==
		// "The Bridge over the River Kwai"

		// titleCase("jaws");
		// titleCase("david copperfield");
		// titleCase("war and peace");
		// titleCase("the bridge over the river kwai");

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm");
//		System.err.println(sdf.format(Calendar.getInstance().getTime()));
		
//		String asd = "在猫狸理财活动期间，您总共消费{0}钻石，获得{1}钻石的返利，详细清单如下：";
//        Object[] params = {"121312" , 22};
//        for (int i = 0; i < params.length; i++) {
//        	asd = asd.replace("{"+i+"}", params[i].toString());
//		}		
//        System.err.println(asd);

		
		
//	    String bas = "eyJzdGF0dXMiOiAiNCIsICJwYXlfbW9uZXkiOiAiMS4wMCIsICJmcmVlX2Ftb3VudCI6ICIwIiwgInBheV9hbW91bnQiOiAiMCIsICJwYXlfY3VycmVuY3kiOiAiQ05ZIiwgInByaWNlIjogIjEuMDAiLCAicGF5X2ZyZWVfbW9uZXkiOiAiMC4wMCIsICJjdXJyZW5jeSI6ICJDTlkiLCAiY3JlYXRlX3RpbWUiOiAiMTQ5NTAwODQ3MSIsICJwYXlfY255IjogIjEuMDAiLCAiZGlzY291bnRfbW9uZXkiOiAiMC4wMCIsICJleHBpcmVfdGltZSI6ICIxNDk1MDE1NjcxIiwgInBheV9tZXRob2QiOiAid2VpeGlucGF5IiwgImlkIjogImFlYnZzaGFlMjRkN3V4d2Utby0xNzA1MTcxNjA3NTEiLCAiYnV5ZXJfaWQiOiAiYWViZnJudHQ2aW5kaG9icCJ9";
//	    System.err.println(base64(bas));
	    
//		System.err.println("10.63.136.11".matches("10.63.136.11"));
		
		stringTest();
		
		
		
		
	}
	
//		http://www.runoob.com/java/java-string-intern.html
//	    http://www.cnblogs.com/forget406/p/5698731.html
	public static void stringTest(){
        String s1 = "123java";
        String s2 = "123" + "java";
        String s3 = 123 + "java";
        String s4 = '1' + 23 + "java";
        String s5 = "123ja" + 'v' + 'a';
        String s6 = new String("123java");
        String s7 = new String("123" + "java");
        String s8 = s6.intern();
        String s9 = "123";
        String s10 = "java";
//        final String s9 = "123";
//        final String s10 = "java";
        String s11 = s9 + s10;
        String s12 = s7.intern();
        String s13 = s11.intern();
        
        System.out.println(s1 == s2);   //true
        System.out.println(s1 == s3);   //true
        System.out.println(s1 == s4);   //false -----------------
        System.out.println(s1 == s5);   //true
        System.out.println(s1 == s6);   //false
        System.out.println(s6 == s7);   //false
        System.out.println(s1 == s7);   //false
        System.out.println(s6 == s8);   //false
        System.out.println(s1 == s8);   //true  -------------------
        System.out.println(s1 == s11);  //false -------------------
        System.out.println(s8 == s12);  //true  -------------------
        System.out.println(s12 == s13); //true  
        
        
        String a = "aaa";
		
        String b = new String("aaa");
        System.err.println( a == b);
        System.err.println( a == b.intern());

        System.err.println(s1 == s11);
	}
	

	public static String titleCase(String title) {
		String[] temp = title.split(" ");
		StringBuilder a = new StringBuilder();
		for (int i = 0; i < temp.length; i++) {
			String asd = temp[i];
			a.append(temp[i].substring(0, 1).toUpperCase());
			if (temp[i].length() > 1) {
				a.append(temp[i].substring(1, temp[i].length()).toLowerCase());
			}
			if (i != temp.length - 1) {
				a.append(" ");
			}
		}
		System.err.println(a.toString());
		return a.toString();
	}

	
	
	public static String base64(String str){
		BASE64Decoder decoder = new BASE64Decoder(); 
		try {
			byte[] msgBytes = decoder.decodeBuffer(str);
			String msgStr = new String(msgBytes);
			msgStr = convert(msgStr);
			return msgStr;
		} catch (IOException e) {
			return null;
		}
	}
	
	
	public static String convert(String utfString){  
		String[] asciis = utfString.split ("\\\\u");
		StringBuffer nativeValueSb = new StringBuffer("");
		nativeValueSb.append(asciis[0]);
        try{
            for ( int i = 1; i < asciis.length; i++ )
            {
                String code = asciis[i];
                nativeValueSb.append((char) Integer.parseInt (code.substring (0, 4), 16));
                if (code.length () > 4)
                {
                	nativeValueSb.append(code.substring (4, code.length ()));
                }
            }
        }
        catch (NumberFormatException e)
        {
            return utfString;
        }
        return nativeValueSb.toString();
	}
	
	

}
