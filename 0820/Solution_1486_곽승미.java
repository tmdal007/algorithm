package day0820;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1486_곽승미 {
	
	static ArrayList<int[]> possible = new ArrayList<>();
	static int cnt = 0;
	static int N,B;
	static int[] heights, sort_hegihts;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/s_1486_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			heights = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				heights[n] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(heights);
			
			for(int i=1; i<=N; i++) {
				sort_hegihts = new int[N];
				for(int j=N-1; j>N-1-i; j--) {
					sort_hegihts[j] = 1;
				}
				do {
					int total_heights = 0;
					for(int k=0; k<sort_hegihts.length; k++) {
						if(sort_hegihts[k] == 1) {
							total_heights += heights[k];
						}
					}
					// 키의 합이 탑의 높이보다 크거나 같은 경우 저장
					if(total_heights >= B) {
						possible.add(new int[] {cnt++,total_heights});
					}

				} while (np(sort_hegihts));
				
			}
			// 최소 차이 구하기
			int min_diff = Integer.MAX_VALUE;
			for(int i=0; i<possible.size(); i++) {
				int[] diff = possible.get(i);
				
				int diff_heights = Math.abs(B - diff[1]);
				if(min_diff > diff_heights) {
					min_diff = diff_heights;
				}
			}
			System.out.println("#"+tc+" "+min_diff);	
		}
	}

	static boolean np(int[] p) {
			
		int N = p.length;
		// step1) 꼭대기 찾기
		int i = N-1;
		while(i>0 && p[i-1]>=p[i]) --i;
		
		if(i==0) return false;
		
		// step2) 꼭대기 앞 교환위치에 교활할 값(i-1위치 값보다 큰 값 중 가장 작은 값) 자리 찾기
		int j = N-1;
		while(p[i-1] >= p[j]) j--;
		
		// step3) 두 위치의 수 교환
		swap(p,i-1, j);
		
		// step4) 꼭대기부터 맨 뒤까지 오름차순의 형태로 만듦
		int k = N-1;
		while(i<k) {
			swap(p, i++, k--);
		}
		return true;
	}

	static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}
