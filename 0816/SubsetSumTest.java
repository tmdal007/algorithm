package day0816;

import java.util.Scanner;

public class SubsetSumTest {

	static int N, S,input[];
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 원소 개수
		S = sc.nextInt(); // 목표 합
		input = new int[N];
		isSelected = new boolean[N]; // 각 원소가 부분집합의 구성에 포함되었는지 여부
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		generateSubset3(0,0,0);
	}
	private static void generateSubset(int cnt) { // cnt : 직전까지 처리한 원소 개수
		
		if(cnt == N) { // 모든 원소가 처리되었다면
			
			int sum = 0;
			for (int i = 0; i < N; i++) { // 모든 원소의 선택/비선택 여부 확인
				if(isSelected[i]) sum += input[i];
			} // 현 부분집합의 선택된 원소들의 합 구하기
			if(sum == S) {
				for (int i = 0; i < N; i++) { // 모든 원소의 선택/비선택 여부 확인
					System.out.print((isSelected[i] ? input[i]:"")+"\t");
				}
				System.out.println();
			}
			return;
		}
		//선택
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		
		//비선택
		isSelected[cnt] = false;
		generateSubset(cnt+1);
	}
	private static void generateSubset2(int cnt) { // cnt : 직전까지 처리한 원소 개수
		
		if(cnt == N) { // 모든 원소가 처리되었다면
			
			int sum = 0, eCnt = 0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) 
					sum += input[i];
					eCnt++;
			} // 현 부분집합의 선택된 원소들의 합 구하기
			if(eCnt >0 && sum == S) {
				for (int i = 0; i < N; i++) { // 모든 원소의 선택/비선택 여부 확인
					System.out.print((isSelected[i] ? input[i]+"\t":""));
				}
				System.out.println();
			}
			return;
		}
		//비선택
		isSelected[cnt] = false;
		generateSubset2(cnt+1);
		
		//선택
		isSelected[cnt] = true;
		generateSubset2(cnt+1);
		
	}
private static void generateSubset3(int cnt, int sum, int eCnt) { // cnt : 직전까지 처리한 원소 개수
																// sum : 직전까지 부분집합의 합
																// eCnt : 직전까지 부분집합 구성 원소의 개수
		if(cnt == N) { // 모든 원소가 처리되었다면
		
			if(eCnt >0 && sum == S) {
				// 부분집합 완성
				for (int i = 0; i < N; i++) { // 모든 원소의 선택/비선택 여부 확인
					System.out.print((isSelected[i] ? input[i]+"\t":""));
				}
				System.out.println();
			}
			return;
		}
		//선택
		isSelected[cnt] = true;
		generateSubset3(cnt+1, sum+input[cnt], eCnt+1);
		
		//비선택
		isSelected[cnt] = false;
		generateSubset3(cnt+1, sum, eCnt);
		
	}
}
