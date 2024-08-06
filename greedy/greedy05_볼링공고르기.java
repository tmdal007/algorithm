package algorithm.day_0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class greedy05_볼링공고르기 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ArrayList<Integer> weights = new ArrayList<>();
		for(int i=0; i<N; i++) {
			weights.add(Integer.parseInt(st.nextToken()));
		}
		int[] check = new int[11]; // 볼링 공 무게
		for(int x: weights) {
			check[x]++;
		}
		
		int res = 0;
		// 경우의 수 구하기
		for(int i=1; i<M+1; i++) {
			N -= check[i];
			res += check[i] * N; 
		} 
		
		System.out.println(res);
	}
}
