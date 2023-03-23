package leetCode;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

	public static void main(String[] args) {
		// 日期測試 localDate轉客製化字串YYYYMMDD
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");
		String formattedString = localDate.format(formatter);
		System.out.println(formattedString);

		// 該月最後一天
		LocalDate endDay = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
		System.out.println(endDay);
// Timestamp
		System.out.println(new Timestamp(System.currentTimeMillis()));
	}

}
