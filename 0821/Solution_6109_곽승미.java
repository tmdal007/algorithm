package day0821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6109_곽승미 {
	static int[][] map;
	static int N;
	static String order;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/s_6109_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringBuffer sb = new StringBuffer();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			order = st.nextToken();
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(order.equals("left")) {
				move_left();
			}else if(order.equals("right")) {
				move_right();
			}else if(order.equals("up")) {
				move_up();
			}else {
				move_down();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(map[i][j]).append(" ");
				}
				if(i<N-1) {
					sb.append("\n");
				}
			}
			System.out.println("#"+tc+"\n"+sb.toString());
		}
		
	}
	static void move_left() {
	    for (int i = 0; i < N; i++) {
	        boolean[] check = new boolean[N]; // 각 행에 대해 체크 배열을 초기화
	        for (int j = 1; j < N; j++) {
	            if (map[i][j] != 0) { // 현재 위치의 값이 0이 아닌 경우에만 처리
	                int k = j;
	                while (k > 0 && map[i][k - 1] == 0) {
	                    map[i][k - 1] = map[i][k];
	                    map[i][k] = 0;
	                    k--;
	                }
	                // 두 값을 병합할 수 있는 경우
	                if (k > 0 && map[i][k - 1] == map[i][k] && !check[k - 1]) {
	                    map[i][k - 1] *= 2; // 두 값을 더하는 대신 두 배로 만듦
	                    map[i][k] = 0;
	                    check[k - 1] = true; // 병합된 위치를 체크
	                }
	            }
	        }
	    }
	}
	static void move_right() {
	    for (int i = 0; i < N; i++) {
	        boolean[] check = new boolean[N]; // 각 행에 대해 체크 배열을 초기화
	        for (int j = N-2; j >=0; j--) {
	            if (map[i][j] != 0) { // 현재 위치의 값이 0이 아닌 경우에만 처리
	                int k = j;
	                while (k < N-1 && map[i][k+1] == 0) {
	                    map[i][k+1] = map[i][k];
	                    map[i][k] = 0;
	                    k++;
	                }
	                // 두 값을 병합할 수 있는 경우
	                if (k < N-1 && map[i][k+1] == map[i][k] && !check[k+1]) {
	                    map[i][k+1] *= 2; // 두 값을 더하는 대신 두 배로 만듦
	                    map[i][k] = 0;
	                    check[k+1] = true; // 병합된 위치를 체크
	                }
	            }
	        }
	    }
	}
	static void move_up() {
	    for (int i = 0; i < N; i++) {
	        boolean[] check = new boolean[N]; // 각 행에 대해 체크 배열을 초기화
	        for (int j = 1; j < N; j++) {
	            if (map[j][i] != 0) { // 현재 위치의 값이 0이 아닌 경우에만 처리
	                int k = j;
	                while (k > 0 && map[k-1][i] == 0) {
	                    map[k-1][i] = map[k][i];
	                    map[k][i] = 0;
	                    k--;
	                }
	                // 두 값을 병합할 수 있는 경우
	                if (k > 0 && map[k-1][i] == map[k][i] && !check[k - 1]) {
	                    map[k-1][i] *= 2; // 두 값을 더하는 대신 두 배로 만듦
	                    map[k][i] = 0;
	                    check[k - 1] = true; // 병합된 위치를 체크
	                }
	            }
	        }
	    }
	}
	static void move_down() {
	    for (int i = 0; i < N; i++) {
	        boolean[] check = new boolean[N]; // 각 행에 대해 체크 배열을 초기화
	        for (int j = N-2; j >=0; j--) {
	            if (map[j][i] != 0) { // 현재 위치의 값이 0이 아닌 경우에만 처리
	                int k = j;
	                while (k < N-1 && map[k+1][i] == 0) {
	                    map[k+1][i] = map[k][i];
	                    map[k][i] = 0;
	                    k++;
	                }
	                // 두 값을 병합할 수 있는 경우
	                if (k < N-1 && map[k+1][i] == map[k][i] && !check[k+1]) {
	                    map[k+1][i] *= 2; // 두 값을 더하는 대신 두 배로 만듦
	                    map[k][i] = 0;
	                    check[k+1] = true; // 병합된 위치를 체크
	                }
	            }
	        }
	    }
	}

}
