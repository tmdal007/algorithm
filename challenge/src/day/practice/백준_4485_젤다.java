package day.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 백준 4485. 녹색 옷 입은 애가 젤다지?
 */
public class 백준_4485_젤다{

    static int N, map[][]; 
    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 최단 경로 계산 후 결과 출력
            System.out.println("Problem " + tc + ": " + getMinTime(0, 0, N - 1, N - 1));
            tc++; 
        }
    }

    static int getMinTime(int sr, int sc, int er, int ec) {
        final int INF = Integer.MAX_VALUE; // 무한대 값 설정
        boolean[][] visited = new boolean[N][N]; // 방문 여부 배열
        int[][] minTime = new int[N][N]; // 최소 시간 저장 배열
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2])); // 우선순위 큐

        // minTime 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                minTime[i][j] = INF;
            }
        }

        minTime[sr][sc] = map[sr][sc]; // 시작점 최소 시간 설정
        pq.offer(new int[]{sr, sc, minTime[sr][sc]}); // 시작점 큐에 추가

        while (!pq.isEmpty()) {
            int[] v = pq.poll(); // 우선순위 큐에서 현재 위치 추출
            int r = v[0]; // 현재 행
            int c = v[1]; // 현재 열
            int time = v[2]; // 현재까지의 최소 시간

            if (visited[r][c]) continue; // 이미 방문한 곳이면 건너뜀
            visited[r][c] = true; // 현재 위치 방문 처리

            if (r == er && c == ec) return time;  // 도착점에 도달하면 최소 시간 반환

            // 상하좌우 이동
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d]; 
                int nc = c + dc[d]; 
                
                // 범위 체크 및 방문 여부 확인
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    int newTime = time + map[nr][nc]; 
                    if (newTime < minTime[nr][nc]) { // 최소 시간 업데이트
                        minTime[nr][nc] = newTime;
                        pq.offer(new int[]{nr, nc, newTime}); // 큐에 추가
                    }
                }
            }
        }
        return -1; // 도착할 수 없는 경우
    }
}