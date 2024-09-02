package day0902;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10966_물놀이를가자 {
	static int N,M,cnt;
	static char[][] map; 
	static int[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_10966_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new int[N][M];
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			int result = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 'L') {
						bfs(i,j);
					}
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					result += visited[i][j];
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = -1;
			}
		}
		visited[x][y] = 0;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			for(int i=0; i<4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				if(map[nx][ny] == 'W') {
					visited[nx][ny] = visited[x][y]+1;
					continue;

				}
				if(map[nx][ny] == 'L' && visited[nx][ny] == -1) {
					q.offer(new int[] {nx,ny});
					visited[nx][ny] = visited[x][y]+1;
				}
			}
		}
//		return cnt;
	}
}
