package day.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2146_다리만들기 {
	
	static int N, map[][];
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i,j, cnt);
					cnt++;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 0) {
					visited = new boolean[N][N];
					int res = bridge(i, j);
					
					if(res == -1) continue;
					if(min > res) {
						min = res;
					}
				}
			}
		}
		System.out.println(min-1);
		
	}
	static void bfs(int x, int y, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = true;
		map[x][y] = cnt;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			
			for (int d = 0; d < 4; d++) {
				int nx = curX + dx[d];
				int ny = curY + dy[d];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					map[nx][ny] = cnt;
					q.offer(new int[] {nx,ny});
				}
			}
		}
	}
	static int bridge(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y,0});
		int num = map[x][y];
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			int length = cur[2];
			
			if(map[curX][curY] != 0 && map[curX][curY] != num) {
				return length;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = curX + dx[d];
				int ny = curY + dy[d];
				
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx,ny,length+1});
				}
			}
		}
		return -1;
	}
}
