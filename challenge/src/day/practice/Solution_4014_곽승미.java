package day.practice;

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
			res = 0; // 최종 결과값
			
			 // 행 검사
            for (int i = 0; i < N; i++) {
                if (cal(map[i], N, X)) {
                    res++; // 활주로 건설 가능이면 증가
                }
            }

            // 열 검사
            for (int i = 0; i < N; i++) {
                int[] col = new int[N];
                for (int j = 0; j < N; j++) {
                    col[j] = map[j][i]; // 각 열을 추출하여 배열로 저장
                }
                if (cal(col, N, X)) {
                    res++; // 활주로 건설 가능이면 증가
                }
            }
	
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(res);
			System.out.println(sb.toString());
		}
		
	}
	public static boolean cal(int[] line, int N, int X) {
		int cnt = 1;
		
		for (int i = 1; i < N; i++) {
			if(line[i] == line[i-1]) {
				cnt++; // 높이가 같으면 cnt 증가
			} else if(line[i] == line[i-1] + 1) { // 오르막 경사 확인
				if(cnt < X) {
					return false;
				}
				cnt = 1; // 경사로 설치하면 초기화
			} else if(line[i] == line[i-1] - 1) { // 내리막 경사 확인
				if(i+X-1 >= N) {
					return false;
				}
				for (int j = i; j < i+X; j++) { // 내리막 경사의 높이가 일정하지 않으면 false
					if(line[j] != line[i]) {
						return false;
					}
				}
				i += X - 1; // 경사로 설치했으니 해당 구간 넘기기 
				cnt = 0; // 0으로 초기화
			} else {
				return false; // 높이 차이가 1이 아니면 false
			}
		}
		return true; // 경사로 설치가 된다면 true
	}
}