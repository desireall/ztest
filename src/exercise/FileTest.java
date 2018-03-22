package exercise;

import java.io.File;
import java.net.URL;

public class FileTest {
	private static String serverlistFile = "serverlist.xml";
	
	
	public static void main(String[] args) {
//		test();
		test2();
	}

	
	
	public static void test(){
		URL serverlistCfgURL = ClassLoader.getSystemResource(serverlistFile);
		if(serverlistCfgURL == null){
			System.exit(-1);
		}
		serverlistFile = serverlistCfgURL.getFile();
		
		System.err.println(serverlistFile);
		System.err.println(new File(serverlistFile).getParent());
		
		System.err.println(serverlistFile.replace("/" + serverlistFile, ""));
		
		File file = new File(serverlistFile.replace("/" + serverlistFile, ""));
		System.err.println(file.exists());
	}
	
	public static void test2(){
		   File directory = new File("");
	        String path = null;
	        try {
	            path = directory.getCanonicalPath();
	            System.err.println(path);
	        }catch(Exception e){
	        	
	        }
	        
	        
	       System.err.println(FileTest.class.getResource("/").getPath());
	}
}
