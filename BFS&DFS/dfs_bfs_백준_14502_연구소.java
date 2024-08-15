package day0814;
/*
 * 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값 구하기
 * BFS/DFS로 풀이
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class dfs_bfs_백준_14502_연구소 {

    public static int n, m, result = 0;
    public static int[][] arr = new int[8][8]; // 초기 맵 배열
    public static int[][] temp = new int[8][8]; // 벽을 설치한 뒤의 맵 배열
    
    // 4가지 이동 방향에 대한 배열 (상, 우, 하, 좌)
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    // BFS를 이용해 각 바이러스가 사방으로 퍼지도록 하기
    public static void spreadVirus() {
        Queue<int[]> queue = new LinkedList<>();

        // 모든 바이러스 위치를 큐에 넣기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 바이러스 확산
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) { // 상하좌우로 퍼질 수 있는지 확인
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 내에 있고 빈 칸이면 바이러스를 퍼뜨림
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && temp[nx][ny] == 0) {
                    temp[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny}); // 바이러스가 퍼진 곳에서도 확산하도록 큐에 추가
                }
            }
        }
    }

    // 현재 맵에서 안전 영역의 크기 계산
    public static int getScore() {
        int score = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    score++;
                }
            }
        }
        return score;
    }

    // DFS를 통해 모든 울타리 배치를 시도 후 최대 안전 영역 찾기
    public static void dfs(int count) { // count는 울타리 개수
        // 울타리가 3개 설치된 경우
        if (count == 3) {
            // 임시 맵 배열에 현재 맵 상태를 복사
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = arr[i][j];
                }
            }
            // 바이러스를 퍼뜨림
            spreadVirus();
            // 안전 영역의 최대값 업데이트
            result = Math.max(result, getScore());
            return;
        }
        // 빈 공간에 울타리를 설치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) { // 빈 공간이면
                    arr[i][j] = 1;  // 울타리 설치
                    count++;		// 울타리 개수 증가
                    dfs(count);  // 다음 울타리 설치
                    arr[i][j] = 0;  // 설치된 울타리 제거 
                    count--;		// 울타리 개수 원복
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//    	System.setIn(new FileInputStream("res/input_14502.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        
        System.out.println(result);
    }

}
