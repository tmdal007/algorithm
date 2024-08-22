package day0822;
/*
 * SWEA 1861. 정사각형 방
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_곽승미 {
	
	static int N,max_cnt,room;
	static int[][] map;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}; // 상,하,좌,우
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/s_1861_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringBuffer sb = new StringBuffer();
			N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max_cnt = Integer.MIN_VALUE;
			room = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					find(i,j,1);
				}
			}

			sb.append("#").append(tc).append(" ").append(room).append(" ").append(max_cnt);
			System.out.println(sb.toString());
		}
	}
	
	static int find(int i, int j, int cnt) {
		// 상,하,좌,우 이동
		for(int d=0; d<4; d++) {
			int ni = i + deltas[d][0];
			int nj = j + deltas[d][1];
			
			// map 범위 밖으로 나가거나 이동하려는 방이 현재 방의 +1이 아닌 경우 continue
			if(ni<0 || ni>=N || nj<0 || nj>=N || ( map[i][j]+1 != map[ni][nj])) {
				continue;
			}
			
			// 다음 위치의 좌표와 이동할 수 있는 방개수+1-> find 함수 호출
			cnt = find(ni,nj,cnt+1);
			
			// 이동할 수 있는 위치를 모두 이동했다면 최대 방의 개수와 출발 위치 저장
			if(max_cnt < cnt) {
				max_cnt = cnt;
				room = map[i][j];
				
			}else if(max_cnt == cnt && room > map[i][j]) {
				max_cnt = cnt;
				room = map[i][j];
			}
			
			break;
		}
		return cnt;
	}
}
	
