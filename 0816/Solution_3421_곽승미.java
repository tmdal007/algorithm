package day0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// SWEA 3421. 수제 버거 장인
public class Solution_3421_곽승미 {
	
	static int N,M,max_cnt;
	static boolean[] isSelected;
	static int[][] list;
	static int res = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/s_3421.txt"));
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			isSelected = new boolean[N];
			list = new int[M][];
			if(M != 0) {
				for(int i=0; i<M; i++) {
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken())-1;
					int b = Integer.parseInt(st.nextToken())-1;
					list[i] = new int[] {a,b};
				}
			}else {
				list = null;
			}

			generateSubset(0);

			sb.append("#").append(t).append(" ").append(res).append("\n");
			res = 0;
		}

	}
	static void generateSubset(int cnt) {
		if(cnt == N) { // 부분집합 완성
			if(list != null) {
				for(int i=0; i<list.length; i++) {
					if(isSelected[list[i][0]] && isSelected[list[i][1]]) {
						return;
					}
				}
			}
			res++;
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
