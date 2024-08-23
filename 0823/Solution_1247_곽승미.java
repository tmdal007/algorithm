package day0823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_곽승미 {

	static int[][] com,house,list; // 회사, 집, 고객 좌표
	static int[] idx,num;
	static int N,minValue;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/s_1247_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringBuffer sb = new StringBuffer();
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			com = new int[1][2];
			house = new int[1][2];
			idx = new int[N];
			num = new int[N];
			
			// 회사와 집의 좌표 저장
			com[0][0] = Integer.parseInt(st.nextToken());
			com[0][1] = Integer.parseInt(st.nextToken());
			house[0][0] = Integer.parseInt(st.nextToken());
			house[0][1] = Integer.parseInt(st.nextToken());
			
			// 고객의 좌표 리스트 저장
			list = new int[N][2];
			for(int n=0; n<N; n++) {
				list[n][0] = Integer.parseInt(st.nextToken());
				list[n][1] = Integer.parseInt(st.nextToken());
				num[n] = n;
			}
			visited = new boolean[N];
			minValue = Integer.MAX_VALUE;
			
			comb(0);
			
			sb.append("#").append(tc).append(" ").append(minValue);
			
			System.out.println(sb.toString());
		}
	}
	
	static void comb(int cnt) {
		if(cnt == N) { // 조합이 생성되었을 때
//			System.out.println(Arrays.toString(idx));
			
			// 거리 계산(회사 -> 고객 -> 집)
			int distance = 0;
			distance += (Math.abs(com[0][0] - list[idx[0]][0]) + Math.abs(Math.abs(com[0][1] - list[idx[0]][1])));
			for(int s=0; s<idx.length-1; s++) {
				distance += (Math.abs(list[idx[s]][0] - list[idx[s+1]][0]) + Math.abs(list[idx[s]][1] - list[idx[s+1]][1]));
			}
			distance += (Math.abs(list[idx[idx.length-1]][0] - house[0][0]) + Math.abs(Math.abs(list[idx[idx.length-1]][1] - house[0][1])));

			// 최소 거리 계산
			if(minValue > distance) {
				minValue = distance;
			}
//			System.out.println(distance + " " + minValue);
			return;
		}
		
		// N개의 집 순서 조합 생성
		for(int i=0; i<N; i++) {
			if(visited[i] == true) continue;
			idx[cnt] = num[i];
			visited[i] = true;
			comb(cnt+1);
			visited[i] = false;
		}
	}

}
