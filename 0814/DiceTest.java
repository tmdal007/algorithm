package day0814;

import java.util.Arrays;
import java.util.Scanner;

public class DiceTest {
	static int[] numbers;
	static int N,totalCnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 주사위 던진 횟수(1<=N<=6);
		int M = sc.nextInt(); // 던지기 모드(1=중복순열, 2=순열. 3=중복조합.4=조합)
	
		numbers = new int[N];
		totalCnt = 0;
		
		switch (M) {
			case 1:
				dice1(0);
				break;
			case 2:
				dice2(0, new boolean[7]);
				break;
			case 3:
				dice3(0, 1);
				break;
			case 4:
				dice4(0, 1);
				break;
			}
		System.out.println("총 경우의 수: "+totalCnt);
	}
	// 주사위 던지기1 : 중복 순열, 6^3 = 216
	private static void dice1(int cnt) {
		if(cnt==N) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = 1; i <= 6; i++) { //가능한 주사위의 눈 시도
			numbers[cnt] = i;
			dice1(cnt+1);
		}
		
	}
	// 주사위 던지기2 : 순열, nPr ==>6P3 : 6*5*4 = 120
	private static void dice2(int cnt, boolean[] isSelected) { // cnt:기존까지 뽑은 수의 개수
		if(cnt==N) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = 1; i <= 6; i++) { //가능한 주사위의 눈 시도
			if(isSelected[i]) continue;
			isSelected[i] = true;
			numbers[cnt] = i;
			dice2(cnt+1, isSelected);
			isSelected[i] = false;
		}
		
	}
	// 중복 조합 : nHr : n+4=1Cr R=3 ==> 8C3 = 56
		private static void dice3(int cnt, int start) {
			if(cnt==N) {
				totalCnt++;
				System.out.println(Arrays.toString(numbers));
				return;
			}
			for (int i = start; i <= 6; i++) {
				numbers[cnt] = i;
				dice3(cnt+1, i);
			}
		}
		
	// 조합 : nCr : R=3 ==> 6C3 = 20
	private static void dice4(int cnt, int start) {
		if(cnt==N) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = start; i <= 6; i++) {
			numbers[cnt] = i;
			dice4(cnt+1, i+1);
		}
	}
}
