package day.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성_1029 {
	
	
    static int N, K, result; 
    static int[][] map, visited; 
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 이동 방향 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 크기
            K = Integer.parseInt(st.nextToken()); // 깊이

            map = new int[N][N]; // 지도 높이 저장 배열
            visited = new int[N][N]; // 방문 여부 저장 배열

            int top = 0; // 최고 높이 초기화

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                	map[i][j] = Integer.parseInt(st.nextToken());
                    top = Math.max(top, map[i][j]); // 최고 높이 찾기
                }
            }

            result = 0;

            // DFS 호출
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == top) {
                        dfs(i, j, 1, K);
                    }
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    static void dfs(int x, int y, int dis, int K) {
    	result = Math.max(result, dis); // 최대 거리 갱신

        visited[x][y] = 1; // 방문 표시

        for (int[] d : deltas) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0) {
                if (map[nx][ny] < map[x][y]) { // 현재 위치보다 낮은 위치로만 이동가능
                    dfs(nx, ny, dis + 1, K); // 산을 깎지 않고 이동
                } 
                // 이동하려는 위치의 높이가 현재 위치보다 높지만 K(깎을 수 있는 깊이)가 남은 경우
                else if (K > 0 && map[nx][ny] - map[x][y] < K) { 
                    int h = map[nx][ny];
                    
                    map[nx][ny] = map[x][y] - 1; // 산 깎기
                    dfs(nx, ny, dis + 1, 0); // 산을 깎은 후 이동
                    map[nx][ny] = h; // 원상태로 복구
                }
            }
        }
        visited[x][y] = 0; // 방문 해제
    }
}