package day0823;

import java.util.Arrays;
import java.util.Scanner;

public class AdjMatrixTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		// 무향 그래프
		int[][] adjMatrix = new int[V][V]; // 기본 초기화값 0 : 인접하지 않는 상태
		for (int i = 0; i < E; ++i) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				adjMatrix[to][from] = adjMatrix[from][to] = 1; 
		}
		
		for(int[] adj : adjMatrix) {
			System.out.println(Arrays.toString(adj));
		}
	}

}
