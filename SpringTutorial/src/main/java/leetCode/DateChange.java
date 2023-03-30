package leetCode;

import java.time.LocalDate;
import java.time.chrono.*;
import java.time.format.*;
import java.util.Locale;

public class DateChange {

	public static void main(String[] args) {
		

		//民國年 yyyMMdd 轉 西元年 yyyyMMdd
		System.out.println(transferMinguoDateToADDate("1010101"));

	}
	
	
	/**
	 * Transfer AD date to minguo date.
	 * 西元年 yyyyMMdd 轉 民國年 yyyMMdd
	 *
	 * @param dateString the String dateString
	 * @return the string
	 */
	public static String transferADDateToMinguoDate(String dateString) {
		LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
		return MinguoDate.from(localDate).format(DateTimeFormatter.ofPattern("yyyMMdd"));
	}

	/**
	 * Transfer minguo date to AD date.
	 * 民國年 yyyMMdd 轉 西元年 yyyyMMdd
	 *
	 * @param dateString the String dateString
	 * @return the string
	 */
	public static String transferMinguoDateToADDate(String dateString) {
		Chronology chrono = MinguoChronology.INSTANCE;
		DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient()
		        .appendPattern("yyyMMdd")
		        .toFormatter()
		        .withChronology(chrono)
		        .withDecimalStyle(DecimalStyle.of(Locale.getDefault()));

		ChronoLocalDate chDate = chrono.date(df.parse(dateString));
		return LocalDate.from(chDate).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

}
