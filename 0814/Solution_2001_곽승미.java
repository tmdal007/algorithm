package day0814;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 2001.파리퇴치
public class Solution_2001_곽승미 {
	static int N,M;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_2001_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int maxsum = -1;
			// 파리채 이동 - M*M 크기 내에 있는 최대 파리의 개수 구하기
			// M*M 파리채는 N-M+1까지만 이동 가능
			for(int i=0; i<N-M+1; i++) {
				for(int j=0; j<N-M+1; j++) {
					int sum = 0;
					for(int k=0; k<M; k++) {
						for(int l=0; l<M; l++) {
							sum += map[i+k][j+l];
						}
					}
					if(sum>maxsum) {
						maxsum = sum;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(maxsum).append("\n");
		}
		System.out.println(sb.toString());	
	}
}
