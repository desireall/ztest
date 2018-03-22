package exercise;

import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		
		
		Date lastRefreshTime = Calendar.getInstance().getTime();
		
		
		Date  aa = (Date)lastRefreshTime.clone();
		
		
		System.err.println(lastRefreshTime.compareTo(aa));
		
		
		System.err.println(new Date(1479889701000l));

//		System.err.println(new Date(1479888922519l));

		System.err.println(new Date(1479889701120l));
		
	}
}
