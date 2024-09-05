package day0905;
/*
 * SWEA 5643. 키 순서
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_5643_곽승미 {
	
	static int N,M;
	static int[][] list,reverse_list;
//	static boolean[][] visited;
	static Set<Integer> check, reverse_check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			list = new int[N+1][N+1];
			reverse_list = new int[N+1][N+1];

			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a][b] = 1;
				reverse_list[b][a] = 1;
			}
			int result = 0;
			for(int i=1; i<N+1; i++) {
				check = new HashSet<>();
				reverse_check = new HashSet<>();
				dfs(i,new boolean[N+1]);
				dfs_reverse(i, new boolean[N+1]);
				System.out.println(check);
				System.out.println(reverse_check);
//				for(int c : check) {
//					for(int r : reverse_check) {
//						if(c == r)
//					}
//				}

			}
//			System.out.println(check);
			System.out.println("#"+tc+" "+result);
		}
		
	}
	static void dfs(int cur, boolean[] visited) {
		
		visited[cur] = true;
		
		check.add(cur);
		 
		for (int i = 1; i < N+1; i++) {
			if(list[cur][i] == 0 || visited[i]) continue;
			 
			dfs(i,visited);
		}
	}
	static void dfs_reverse(int cur, boolean[] visited) {
		 
		for (int i = 1; i < N+1; i++) {
			if(reverse_list[i][cur] == 0 || visited[i]) continue;
			reverse_check.add(cur);
//			visited[cur] = true;
			dfs(i,visited);
		}
	}
//	static void dfs_reverse(int cur, boolean[] visited) {
//		
////		visited[cur] = true;
//		
////		reverse_check.add(cur);
//		
//		//자신의 인접정점들 다음 탐색위한 준비!
//		for (int i = 1; i < N+1; i++) {
//			if(reverse_list[i][cur] == 0 || visited[i]) continue;
//			reverse_check.add(i);
//			visited[i] = true;
//			dfs(i,visited);
//		}
//	}
}
  