package day.practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4013_곽승미 {

	static int[][] arr; // 톱니바퀴 상태 저장 배열
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/s_4013_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			
			arr = new int[4][8]; // 4개의 톱니바퀴, 8개 값 저장 배열
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print(arr[i][j]+" ");
				}
//				System.out.println();
			}
			
			// K번 회전
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int n = Integer.parseInt(st.nextToken())-1; // 톱니바퀴 번호
				int d = Integer.parseInt(st.nextToken()); // 회전 방향
				
				int[] dir = new int[4]; // 톱니바퀴 회전방향 저장 배열
				dir[n] = d; // 현재 톱니바퀴 회전 방향 저장
				
				
				// 오른쪽 톱니바퀴 체크
				for (int j = n; j < 3; j++) {
					if(arr[j][2] != arr[j+1][6]) {
						dir[j+1] = -dir[j];
					}
				}
				// 왼쪽 톱니바퀴 체크
				for (int j = n; j > 0; j--) {
					if(arr[j-1][2] != arr[j][6]) {
						dir[j-1] = -dir[j];
					}
				}
				// 회전 수행
				for (int j = 0; j < 4; j++) {
					if(dir[j] != 0) { // 회전해야하는 톱니바퀴만 회전
						rotate(j,dir[j]);
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(arr[0][0]+2*arr[1][0]+
					4*arr[2][0]+8*arr[3][0]).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// 톱니바퀴 회전 메소드
	public static void rotate(int n, int d) {
		if(d == 1) { // 시계 방향 회전
			int T = arr[n][7];
			for (int i = 7; i > 0; i--) {
				arr[n][i] = arr[n][i-1];
			}
			arr[n][0] = T;
		}else { // 반시계 방향 회전
			int T = arr[n][0];
			for (int i = 0; i < 7; i++) {
				arr[n][i] = arr[n][i+1];
			}
			arr[n][7] = T;
		}
	}
}
