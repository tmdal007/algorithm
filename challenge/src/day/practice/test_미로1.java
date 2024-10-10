package day.practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class test_미로1 {
	static int[] dx = {-1,1,0,0}; // 상,하,좌,우
	static int[] dy = {0,0,-1,1};
	static char[][] map; // 맵
	static Queue<int[]> q; //큐

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_123.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			map = new char[16][16];
			q = new LinkedList<>();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 16; i++) {
				String line =  br.readLine();
				for (int j = 0; j < 16; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			
			int cnt = bfs(1,1);
			
			sb.append("#").append(tc).append(" ").append(cnt);
			System.out.println(sb.toString());			
		}
	}

	private static int bfs(int i, int j) {
		q.offer(new int[]{i,j});
		map[i][j] = '1';
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				// 길이 있으면 큐에 추가 후 방문 처리
				if(map[nx][ny] == '0') {
					q.offer(new int[] {nx,ny});
					map[nx][ny] = '1';
				}
				
				if(map[nx][ny] == '3') {
					return 1;
				}
			}
		}

		return 0;
	}
}
