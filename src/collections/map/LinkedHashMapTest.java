package collections.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedHashMapTest {
	
	public static void main(String[] args) throws IOException {
		
//		Map<Integer , Integer> map  = new LinkedHashMap<Integer , Integer>();
//		map.put(1, 1);
//		map.put(2, 1);
//		map.put(3, 1);
//		map.put(4, 1);
//		map.put(2, 2);
//		map.get(4);
//		for (Entry<Integer , Integer> entry : map.entrySet()) {
//			System.err.println(entry.getKey()+"---"+entry.getValue());
//		}
		
		
		File file = new File("E:/yilegameT6/trunk/dev/client/bin");
		
		for(File cfile : file.listFiles())
		{
			if(cfile.isDirectory())continue;
			FileInputStream input =new FileInputStream(cfile);
			byte[] a = new byte[input.available()]; 
			System.err.println(cfile.getName() + "文件长度： "+a.length);
			int result = input.read(a);
			System.err.println(result);
			result = input.read(a);
			System.err.println(result);
		}
	}
}
