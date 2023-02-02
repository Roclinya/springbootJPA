package leetCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class test {

	public static void main(String[] args) throws IOException {
		
//		String message[] = new String[]{"RFILEList","insR6BackTime","content","insR6Insuno","backupFile"};
//		List<String> messageList = Arrays.asList(message);
//		messageList.forEach(e->{
//			System.out.println(e);
//			
//		});
//		
//		File file = new File("");
//		System.out.println(file.list());
//		
//		Map<String,String>[] records = new Map[6];
		
		//日期測試 localDate轉客製化字串YYYYMMDD
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");
		String formattedString = localDate.format(formatter);
		System.out.println(formattedString);

		
		 try(FileInputStream in = new FileInputStream("input.txt");){
			 System.out.println("process");
		 }catch (Exception ex) {
				// 例外處理
			 System.out.println("file not found");
		 }
	}

}
