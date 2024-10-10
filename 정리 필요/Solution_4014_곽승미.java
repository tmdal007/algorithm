package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_4014_곽승미 {
	
	static int res;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_4014_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
		
			int[][] map = new int[N][N];
			
			// 맵 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = 0;
			for (int i = 0; i < N; i++) {
				int cnt = 1;
				boolean flag = false;
				int up = 0, down = 0;
				
				for (int j = 0; j < N-1; j++) {
					if(map[i][j] == map[i][j+1] + 1) { // 올라갈때
						if(cnt >= X) {
							up++;
							cnt = 1;
							flag = true;
						}else {
							flag = false;
							break;
						}
					}else if(map[i][j] == map[i][j+1] - 1) { // 내려갈때
						if(cnt > 1) {
							
						}
						
					}else if(map[i][j] == map[i][j+1]) {
						cnt++;
					}else {
						flag = false;
						break;
					}
				}
				if(cnt == N) {
					res++;
					cnt=1;
				}else if(flag) {
					res++;
				}	
			}
			
			for (int i = 0; i < N; i++) {
				int cnt = 1;
				boolean flag = false;
				int up = 0, down = 0;
				
				for (int j = 0; j < N-1; j++) {
					if(map[j][i] == map[j+1][i] + 1) {// 올라갈때
						if(cnt >= X) {
							up++;
							cnt = 1;
							flag = true;
						}else {
							flag = false;
							break;
						}
					}else if(map[j][i] == map[j+1][i] - 1) {// 내려갈때
						continue;
					}else if(map[j][i] == map[j+1][i]) { 
						cnt++;
					}else {
						flag = false;
						break;
					}
				}
				if(cnt == N) {
					res++;
					cnt=1;
				}else if(flag) {
					res++;
				}	
			}
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(res);
			System.out.println(sb.toString());
		}
		
	}

}

//for (int i = 0; i < N; i++) {
//boolean flag = true;
//int cnt = 1;
//int rup = 0, lup = 0;
//for (int j = 0; j < N-1; j++) {
//	if(map[i][j] == map[i][j+1]) {
//		cnt++;
//	}else if(map[i][j] == map[i][j+1] + 1) {// 위로 하나 올라갈때
//		if(cnt >= X) {
//			rup++;
//			flag = true;
//			cnt = 1;
//		}
//		else {
//			cnt = 1;
//			flag = false;
//			break;
//		}
//	}else {
//		cnt = 1;
//		flag = false;
//		break;
//	}
//}
//for (int j = N-1; j >=1; j--) {
//	if(map[i][j] == map[i][j-1]) {
//		cnt++;
//	}else if(map[i][j]+1 == map[i][j-1]) {// 위로 하나 올라갈때
//		if(cnt >= X) {
//			lup++;
//			flag = true;
//			cnt = 1;
//		}
//		else {
//			cnt = 1;
//			flag = false;
//			break;
//		}
//	}else {
//		cnt = 1;
//		flag = false;
//		break;
//	}
//	if(lup > 1) {
//		flag = false;
//		break;
//	}
//}
//if(cnt == N) {
//	res++;
//	cnt=1;
//}else if(flag) {
//	res++;
//}
//}
//for (int i = 0; i < N; i++) {
//boolean flag2 = false;
//int cnt = 1;
//int rup = 0, lup = 0;
//for (int j = 0; j < N-1; j++) {
//	if(map[j][i] == map[j+1][i]) {
//		cnt++;
//	}else if(map[j][i] + 1 == map[j+1][i]) {
//		if(cnt >= X) {
//			rup++;
//			flag2 = true;
//			cnt = 1;
//		}
//		else {
//			cnt = 1;
//			flag2 = false;
//			break;
//		}
//	}else {
//		cnt = 1;
//		flag2 = false;
//		break;
//	}
//}
//for (int j = N-1; j >=1; j--) {
//	if(map[j][i] == map[j-1][i]) {
//		cnt++;
//	}else if(map[j][i] + 1 == map[j-1][i]) {// 위로 올라갈때
//		if(cnt >= X) {
//			lup++;
//			flag2 = true;
//			cnt = 1;
//		}
//		else {
//			cnt = 1;
//			flag2 = false;
//			break;
//		}
//	}else {
//		cnt = 1;
//		flag2 = false;
//		break;
//	}
//	if(lup > 1) {
//		flag2 = false;
//		break;
//	}
//}
//if(cnt == N) {
//	res++;
//	cnt=1;
//}else if(flag2) {
//	res++;
//}
//}
