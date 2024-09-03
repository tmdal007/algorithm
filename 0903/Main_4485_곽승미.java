package day0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 백준 4485. 녹색 옷 입은 애가 젤다지?
 */
public class Main_4485_곽승미 {

    static int N, map[][];
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break; // 입력 끝 체크

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("Problem " + tc + ": " + getMinTime(0, 0, N - 1, N - 1));
            tc++;
        }
    }

    static int getMinTime(int sr, int sc, int er, int ec) {
        final int INF = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[N][N];
        int[][] minTime = new int[N][N];
        PriorityQueue<int[]> pQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                minTime[i][j] = INF;
            }
        }

        minTime[sr][sc] = map[sr][sc];
        pQueue.offer(new int[]{sr, sc, minTime[sr][sc]});

        while (!pQueue.isEmpty()) {

            int[] stopOver = pQueue.poll();
            int r = stopOver[0];
            int c = stopOver[1];
            int time = stopOver[2];

            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (r == er && c == ec) return time;  // 최종 도착점에 도달 시의 최소 시간 반환

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    int newTime = time + map[nr][nc];
                    if (newTime < minTime[nr][nc]) {
                        minTime[nr][nc] = newTime;
                        pQueue.offer(new int[]{nr, nc, newTime});
                    }
                }
            }
        }
        return -1;
    }
}
