package leetCode;

import java.text.*;
import java.util.Date;

public class DateFormat {

	public static void main(String[] args) throws ParseException {
		String testDate = "2023/04/24";

		Object sqldate = stringToSqlDate(testDate);
		


		//SQL date
		System.out.println("SQL date for now");
		System.out.println(new java.sql.Date(System.currentTimeMillis()));
		System.out.println("Util date for now");
		System.out.println(new Date(System.currentTimeMillis()));
		//util date
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Date d = new Date();
//		String result = sdf.format(d);
//		System.out.println(result);
		String testDate2 = "2023-05-24";
		System.out.println("---SQL Date default with dash-");
		System.out.println(java.sql.Date.valueOf(testDate2));

	}

	private static Date stringToSqlDate(String testDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date d = sdf.parse(testDate);
		long time = d.getTime();
		System.out.println(time);
		Date sqlDate = new java.sql.Date(time);
		System.out.println("SQL Date : " + sqlDate);
		return sqlDate;

	}

}
