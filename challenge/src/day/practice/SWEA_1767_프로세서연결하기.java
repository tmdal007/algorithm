package day.practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기 {
	
	static int N, map[][], maxCore, minLength;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static List<int[]> core;
 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
	        map = new int[N][N];
	        core = new ArrayList<>();
	        maxCore = 0;
	        minLength = Integer.MAX_VALUE;
	        
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                	map[i][j] = Integer.parseInt(st.nextToken());
                	// 위치가 가장자리가 아닌 코어 저장
                	if (i > 0 && i < N - 1 && j > 0 && j < N - 1 && map[i][j] == 1) {
                        core.add(new int[]{i, j});
                    }
                }
            }
            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minLength);
		}
	}
	
	// dfs 탐색
	static void dfs(int idx, int cnt, int total) { //인덱스, 코어개수, 전선 총길이
		if(idx == core.size()) {
			if(cnt > maxCore) {
				maxCore = cnt;
				minLength = total;
			}else if(cnt == maxCore) {
				minLength = Math.min(minLength, total);
			}
			return;
		}
		// 현재 처리 중인 코어의 위치
        int x = core.get(idx)[0];
        int y = core.get(idx)[1];

        // 모든 방향에 대해 시도
        for (int d = 0; d < 4; d++) {
            int length = check(x, y, d); // 해당 방향으로 전선 연결할 수 있는지 확인
            if (length != -1) { // 연결 가능하다면
                connect(x, y, d, length, true);  // 전선 연결
                dfs(idx + 1, cnt + 1, total + length);  // 다음 코어로 이동
                connect(x, y, d, length, false); // 전선 해제
            }
        }

        // 연결하지 않고 다음 코어로 이동
        dfs(idx + 1, cnt, total);
	}
	
	// 전선 연결 가능 확인 메서드
	static int check(int x, int y, int d) {
	    int length = 0;
	    int curX = x;
	    int curY = y;

	    while (true) {
	    	curX += dx[d];
	    	curY += dy[d];
	        
	    	// 범위를 벗어나면 길이 반환
	        if (curX < 0 || curX >= N || curY < 0 || curY >= N) {
	            return length;
	        }
	        // 다른 코어나 전선이 있으면 -1 반환
	        if (map[curX][curY] != 0) {
	            return -1;
	        }
	        length++;
	    }
	}
	
	// 전설 연결 및 해제 - 연결 가능하다면 true(2), 아니면 false(0)
	static void connect(int x, int y, int d, int length, boolean b) {
	    int curX = x;
	    int curY = y;

	    for (int i = 0; i < length; i++) {
	    	curX += dx[d];
	    	curY += dy[d];
	        map[curX][curY] = b ? 2 : 0;
	    }
	}
}