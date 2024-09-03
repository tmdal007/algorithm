package day0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 4485. 녹색 옷 입은 애가 젤다지?
 */
public class Main_4485_곽승미 {
	
	static int N;
	static int[][] map;
	static int[] dr = {-1,1,0,0}; // 상,하,좌,우
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 1; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println(getCoin(0, 0, N-1, N-1));
		}
	}
	static int getCoin(int sr, int sc, int er, int ec) {
		final int INF = Integer.MAX_VALUE;
		int[][] minTime = new int[N][N];
		boolean[][] visited = new boolean[N][N]; // 방문한 정점 관리
		
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((a,b)->Integer.compare(a[2], b[2]));
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				minTime[i][j] = INF;
			}
		}
		minTime[sr][sc] = 0;
		pQueue.offer(new int[] {sr,sc,minTime[sr][sc]});
		
		while(!pQueue.isEmpty()) {
			
			int[] stopOver = pQueue.poll();
			int r = stopOver[0];
			int c = stopOver[1];
			int time = stopOver[2];
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			if(r==er && c==ec) return time+map[r][c]; //minTime[er][ec];
		
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc] && minTime[nr][nc] > time+map[nr][nc]) {
					minTime[nr][nc] = time+map[nr][nc];
					pQueue.offer(new int[] {nr,nc,minTime[nr][nc]});
					
				}
			}
		}
		return -1;
	}
}
