package day.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기22 {
	
	static int N, map[][], maxCore,minLength;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static List<int[]> core;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			core = new ArrayList<>();
			maxCore = 0;
			minLength = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(i > 0 && j > 0 && i < N-1 && j < N-1 && map[i][j] == 1) {
						core.add(new int[] {i,j});
					}
				}
			}
			dfs(0,0,0);
			System.out.println("#"+tc+" "+minLength);
		}
	}
	
	static void dfs(int idx, int cnt, int total) { // 인덱스, 코어 개수, 전선 길이
		if(idx == core.size()) {
			if(cnt > maxCore) {
				maxCore = cnt;
				minLength = total;
			}else if(cnt == maxCore) {
				minLength = Math.min(minLength, total);
			}
			return;
		}

		int x = core.get(idx)[0];
		int y = core.get(idx)[1];
		
		
		for (int d = 0; d < 4; d++) {
			
			int length = check(x,y,d);
			
			if(length != -1) {
				connect(x,y,d,length,true);
				dfs(idx+1, cnt+1, total+length);
				connect(x,y,d,length,false);
			}
		}
		dfs(idx+1, cnt, total);
		
	}

	static int check(int x, int y, int dir) {
		int length = 0;
		int cx = x;
		int cy = y;
		
		while(true) {
			cx += dx[dir];
			cy += dy[dir];
			
			if(cx >= N || cx < 0 || cy >= N || cy < 0) {
				return length;
			}
			
			if(map[cx][cy] != 0) {
				return -1;
			}
			length++;
		}
	}
	private static void connect(int x, int y, int d, int length, boolean flag) {
		int cx = x;
		int cy = y;
		
		for (int i = 0; i < length; i++) {
			cx += dx[d];
			cy += dy[d];
			map[cx][cy] = flag ? 2 : 0;
		}
		
	}

}
