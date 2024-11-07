package day.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_젤다 {
	
	static int N,map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = 1;
		boolean flag = true;
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}	
			}
			
			int ans = bfs(0,0,N-1,N-1);
			
			System.out.printf("Problem %d: %d\n", n++, ans);
			
		}
	}
	static int bfs(int sr, int sc, int er, int ec) {
		final int INF = Integer.MAX_VALUE;
		boolean visited[][] = new boolean[N][N];
		int minTime[][] = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
		minTime[0][0] = map[0][0];
		pq.offer(new int[] {0,0,minTime[0][0]});
				
		while(!pq.isEmpty()) {
			int cur[] = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			
			if(r == er && c == ec) {
				return time;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					int t = time + map[nr][nc];
					if(t < minTime[nr][nc]) {
						minTime[nr][nc] = t;
						visited[nr][nc] = true;
						pq.offer(new int[] {nr,nc,minTime[nr][nc]});
					}
				}
			}
		}
		
		return -1;
	}
}
