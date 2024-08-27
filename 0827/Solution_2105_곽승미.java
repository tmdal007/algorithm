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
	static ArrayList<Integer> num;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_2105_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
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
			
			// 모든 좌표에서 dfs 탐색
			// (0열과 N-1열은 마름모(사각형)을 방향대로 진행하면 만들 수 없으므로 제외)
			for(int i=0; i<N; i++) {
				for(int j=1; j<N-1; j++) {
					visited = new boolean[N][N];
					num = new ArrayList<>();
					dfs(i,j, i,j,0, 0);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(max_res);
			System.out.println(sb.toString());
		}
	}
	
	static void dfs(int x, int y, int startX, int startY, int res, int dir) {
		// 사각형 경로가 완성된 경우(방문한 곳이 4개 이상이고, 스타트 위치에 다시 돌아왔을 때)
		if(res > 3 && x == startX && y == startY) {
			max_res = Math.max(max_res, res);
			return;
		}
		// 현재 좌표 유효, 방문하지 않았고, 디저트 num에 존재하지 않으면(중복x)
		if(!visited[x][y] && !num.contains(map[x][y])) {
			visited[x][y] = true;
			num.add(map[x][y]);
			
			// 현재 방향에서 다음 방향으로 dfs 진행
			for(int d=dir; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				// 범위 체크
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				dfs(nx,ny,startX,startY,res+1,d);
			}
			//dfs 빠져나왔을 때 상태 복원
			visited[x][y] = false;
			num.remove(num.size()-1);
		}		
	}
}
