package day0816;

import java.util.Scanner;

public class SubsetTest {

	static int N, input[];
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 원소 개수
		input = new int[N];
		isSelected = new boolean[N]; // 각 원소가 부분집합의 구성에 포함되었는지 여부
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		generateSubset(0);
	}
	private static void generateSubset(int cnt) { // cnt : 직전까지 처리한 원소 개수
		
		if(cnt == N) { // 모든 원소가 처리되었다면
			// 부분집합 완성
			for (int i = 0; i < N; i++) { // 모든 원소의 선택/비선택 여부 확인
				System.out.print((isSelected[i] ? input[i]:"X")+"\t");
			}
			System.out.println();
			return;
		}
		//선택
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		
		//비선택
		isSelected[cnt] = false;
		generateSubset(cnt+1);
	}

}
