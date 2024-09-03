package day0903;
/*
 * SWEA 2117. 홈 방범 서비스
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2117_곽승미 {
	
	static int N,M,map[][];
	static ArrayList<int[]> home;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/s_2117_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			home = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {// 집 위치 저장
						home.add(new int[] {i,j});
					}
				}
			}
			int result = 0;
			// 맵이 집으로 가득차있다면 계산 불필요
			if(N*N == home.size()) {
				result = N*N;
			}else {
				result = cal();
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	// 서비스 제공 받을 수 있는 최대 집들의 수 구하기
	static int cal() {
		int K = N;
		int homeCnt = Integer.MIN_VALUE;
		while(K > 0) {
			if(K==0) break;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int cnt = 0;
					for(int[] h : home) {
						int x = h[0];
						int y = h[1];
						// map을 돌 때 마름모의 중심이라 생각하고 집의 위치와 거리를 계산
						// K 크기의 마름모 내에 위치 하려면 거리가 K-1 이하여야 함
						int d = Math.abs(i-x) + Math.abs(j-y);
						if(K-1 >= d) {
							cnt++;
						}
					}
					int total = cnt*M; // 마름모 내의 집에서 지불할 수 있는 총 비용
					int val = K*K+(K-1)*(K-1); // 홈서비스 운영 비용
					if(val <= total && homeCnt < cnt) {
						homeCnt = cnt;
					}
				}
			}
			K--;
		}
		return homeCnt;
	}
}
