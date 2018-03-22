package test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Entry {

	
	public static void main(String[] args) {
		
		Set<String> set = new HashSet<>();
		set.add("asd");
		set.add("ser");
		set.add("der");
		
		for (int i = 0; i < 3; i++) {
			for (Object string : set.toArray()) {
				System.err.println(string);
			}
		}
		
	}
}
