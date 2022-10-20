package hello;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class LotteryMachine {
	// 挑選隨機數量的球
	// https://www.zhihu.com/question/51843865/answer/127869955
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

		ArrayList<Integer> totalBalls = new ArrayList<>();
		for (int i = 1; i <= nums; i++) {
			totalBalls.add(i);
		}
		System.out.println("遊戲池中所有的球 totalBalls  " + totalBalls);
		HashSet<Integer> randomGetBalls = new HashSet<>();
		Object[] results = Shuffle(pickNums, totalBalls, randomGetBalls);

	}

	public static Object[] Shuffle(int pickNums, ArrayList<Integer> totalBalls, HashSet<Integer> randomGetBalls) {
		for (int i = 1; i <= pickNums; i++) {
			int value = new Random().nextInt(totalBalls.size()) + 1;
			totalBalls.remove(Integer.valueOf(value));
			randomGetBalls.add(value);
		}
		System.out.println("被挑選的球 " + randomGetBalls);
		System.out.println("沒被挑選的球 " + totalBalls);
		return new Object[] { randomGetBalls, totalBalls };

	}

}
