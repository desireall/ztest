package resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

public class FileLoad {
	
	public static void main(String[] args) throws FileNotFoundException {
//		InputStream is = FileLoad.class.getResourceAsStream("common.properties");   
		URL path = FileLoad.class.getClassLoader().getResource("file:/F:/branch/ztest/common.properties");
//		File file = new File("common.properties");
//		URL path = null;
//		try {
//			path = file.toURL();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		InputStream ips = new FileInputStream("common.properties");//IO流读取配置文件  
		System.err.println("//////////////");
		
	}
}
