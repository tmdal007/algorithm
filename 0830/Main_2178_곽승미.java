package day0830;
/*
 * 백준 2178. 미로 탐색
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2178_곽승미 {
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0}; // 상,하,좌,우
	static int[] dy = {0,0,-1,1}; // 상,하,좌,우
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			String line = br.readLine();
			for(int j = 0; j<M; j++) {
				map[i][j] = line.charAt(j) -'0';
			}
		}
		int res = bfs(0,0);
		
		System.out.println(res);
	}
	
	static int bfs(int x, int y) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			x = cur[0];
			y = cur[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >=N || ny < 0 || ny >= M) {
					continue;
				}
				if(map[nx][ny] == 0) {
					continue;
				}
				if(map[nx][ny] == 1 && !visited[nx][ny]) {
					map[nx][ny] = map[x][y] + 1;
					q.offer(new int[] {nx,ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		return map[N-1][M-1];
	}
}
