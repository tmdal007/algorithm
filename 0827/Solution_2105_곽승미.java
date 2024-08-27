package day0827;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * SWEA 2105. 디저트카페
 */
public class Solution_2105_곽승미 {
	
	static boolean[][] visited;
	static int[][] map;
	static int N,max_res;
	static int startX, startY;
	static ArrayList<Integer> num;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_2105_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=1; tc++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max_res = -1;

			for(int i=0; i<N; i++) {
				for(int j=1; j<N-1; j++) {
					startX = i;
					startY = j;
					visited = new boolean[N][N];
					num = new ArrayList<>();
					dfs(i,j, 0, 0);
//					System.out.println(res+ " : " +num);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(max_res);

			System.out.println(sb.toString());
		}
	}
	
	static void dfs(int x, int y, int dir, int res) {
		if(startX == x && startY == y && dir == 4) {
			max_res = Math.max(max_res, res);
			System.out.println(max_res);
		}
		
		num.add(map[x][y]);
		visited[x][y] = true;
		System.out.println(num);
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		

		if(nx < 0 || nx >= N || ny < 0 || ny >= N || num.contains(map[nx][ny])) {
//			dir++;
			visited[x][y] = false;
			num.remove(num.size()-1);
			dfs(x,y,dir+1,res);
		}else {
			dfs(nx,ny,dir,res+1); 
		}
		
//		if(!num.contains(map[nx][ny]) && !visited[nx][ny]) {
//			dfs(nx,ny,dir,res+1); 
//		}

		
	}

}
