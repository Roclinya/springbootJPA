package leetCode;

import java.io.File;

public class FileCheck {

	public static void main(String[] args) {

		String folderPath = "./copyFile";
		String textfilePath = "./copyFile/input.txt";

		File folder = new File(folderPath);
		File textfile = new File(textfilePath);
		System.out.println("folder isFile? " + folder.isFile());
		System.out.println("textfile isFile? " + textfile.isFile());
		if (folder.exists()) {
			File[] listOfFiles = folder.listFiles();
			System.out.println(listOfFiles);
			for (File file : listOfFiles) {
			    if (file.isFile()) {
			        System.out.println(file.getName());
			    }
			}
		}

	}

}
