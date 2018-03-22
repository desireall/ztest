package date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
//		int endDay = 15;
//		Calendar calendar = Calendar.getInstance();
//		if (endDay == 15) {
//			calendar.set(Calendar.DAY_OF_MONTH, endDay);
//		} else {
//			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
//			calendar.set(Calendar.DAY_OF_MONTH, 1);
//		}
//		calendar.set(Calendar.HOUR_OF_DAY, 5);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		calendar.set(Calendar.MILLISECOND, 0);
//		System.err.println((int)(calendar.getTimeInMillis() / 1000));
//
//		System.err.println(sdf.format(calendar.getTime()));

		
		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int lastMonth = month;
		Calendar lastCalendar = Calendar.getInstance();
			System.err.println(sdf.format(lastCalendar.getTime()));
			lastCalendar.add(Calendar.MONTH,  -1);
			System.err.println(sdf.format(lastCalendar.getTime()));
			System.err.println(lastCalendar);
	}

}
