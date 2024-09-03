package day0902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_7465_창용마을의무리의개수 {
	static int T,N,M,answer;
	static List<Integer>[] list = new ArrayList[101];
	static int[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			answer = 0;
			
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<>();
			}
			visited = new int[N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			for(int i=1; i<=N; i++) {
				if(visited[i] == 0) {
					visited[i] = 1;
					dfs(i);
					// 왜 원복이 필요하지 않았는가?
					answer++;
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
	public static void dfs(int idx) {
		for(int i=0; i<list[idx].size(); i++) {
			int next = list[idx].get(i);
			if(visited[next] == 1) continue;
			
			visited[next] = 1;
			dfs(next);
		}
	}

}
