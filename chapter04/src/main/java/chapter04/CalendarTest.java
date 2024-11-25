package chapter04;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {

	public static void main(String[] args) {
		// Calander는 new 키워드를 사용하지 않는다.
		Calendar cal = Calendar.getInstance();
		printDate(cal);		
		
		Locale aLocale = Locale.getDefault(Locale.Category.FORMAT);
		TimeZone tz = TimeZone.getDefault();
		
		System.out.println(aLocale);
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
	}

}
