package day0820;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2805_곽승미 {
	static int N;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/s_2805_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				char[] arr = str.toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.valueOf(arr[j]) - '0';
				}
			}
			int idx = Math.round(N/2);
			int value = 0;
			//0~N/2까지 value의 합
			for(int i=0; i<=idx; i++) {
				for(int j=idx-i; j<=idx+i; j++) {
					value += map[i][j];
                }
			}
			//N/2~N까지 value의 합
			int cnt = 1;
			for(int i=idx+cnt; i<N; i++, cnt++) {
				for(int j=cnt; j<N-cnt; j++) {
					value += map[i][j];
				}
			}
			System.out.println("#"+tc+" "+value);
		}
	}
}