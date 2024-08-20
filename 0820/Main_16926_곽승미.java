package day0820;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_곽승미 {
	static int N,M,R;
	static int[][] map,clone;
	static int[][] deltas = {{0,-1},{1,0},{0,1},{-1,0}}; // 좌,하,우,상
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];

		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0; m<M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		for(int r = 0; r<R; r++) {
			clone = new int[N][M];
			
			for(int i=0; i<N/2; i++) {
				
				boolean flag = true;
				int x = i;
				int y = i;
				int idx = 0;
				
				while(flag) {
					int nx = x + deltas[idx][0];
					int ny = y + deltas[idx][1];

					if(nx>=i && nx<N-i && ny>=i && ny<M-i) {
						clone[nx][ny] = map[x][y];
						x = nx;
						y = ny;
					}else {
						idx = (idx+1)%4;
					}
					if(nx == i && ny == i) {
						flag = false;
					}
				}
			}
			map = clone;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(clone[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
