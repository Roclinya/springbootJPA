package hello;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Shuffle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		final int MaxAmountOfBalls = 100000;

		System.out.println("Enter a number from 1-100k for total balls");
		int nums = scanner.nextInt();

		if (nums > MaxAmountOfBalls) {
			System.out.println("輸入錯誤 請重新輸入");
			nums = scanner.nextInt();
		}

		System.out.println("Enter a number from 1-100k for picking balls");
		int pickNums = scanner.nextInt();

		HashSet<Integer> totalBalls = new HashSet<>();
		for (int i = 1; i <= pickNums; i++) {
			totalBalls.add(i);
		}
		System.out.println("遊戲池中所有的球 totalBalls  " + totalBalls);
	
		
	}



}
