package day0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_곽승미 {
	static int N;
	static int min_score = Integer.MAX_VALUE;
	static int[][] list;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/s_2961.txt"));
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		list = new int[N][2];
		isSelected = new boolean[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
		}
		cal(0);
		System.out.println(min_score);
	}
	static void cal(int cnt) {
		if(cnt == N) {
			int s_score = 1;
			int b_score = 0;
			int n = 0; 
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					n++;
					s_score *= list[i][0];
					b_score += list[i][1];
				}
			}
			if(n == 0) return;
			if(min_score > Math.abs(s_score-b_score)) {
				min_score = Math.abs(s_score-b_score);
			}
			return;
			
		}
		
		isSelected[cnt] = true;
		cal(cnt+1);
		
		isSelected[cnt] = false;
		cal(cnt+1);
	}

}
