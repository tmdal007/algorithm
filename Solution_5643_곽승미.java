package day0905;
/*
 * SWEA 5643. 키 순서
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5643_곽승미 {

    static int N, M;
    static int[][] list, reverse_list;
    static int[] listCnt, relistCnt;  // 큰 사람 수, 작은 사람 수 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            list = new int[N + 1][N + 1];
            reverse_list = new int[N + 1][N + 1];
            listCnt = new int[N + 1];  // 자신보다 큰 사람 수
            relistCnt = new int[N + 1];  // 자신보다 작은 사람 수

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a][b] = 1;  // 정방향
                reverse_list[b][a] = 1;  // 역방향
            }

            // 각 학생에 대해 자신보다 큰 사람 탐색 (정방향 그래프)
            for (int i = 1; i <= N; i++) {
                dfs(i, new boolean[N + 1], list, listCnt);
            }

            // 각 학생에 대해 자신보다 작은 사람 탐색 (역방향 그래프)
            for (int i = 1; i <= N; i++) {
                dfs(i, new boolean[N + 1], reverse_list, relistCnt);
            }

            // 자신보다 큰 사람과 작은 사람의 합이 N-1이면 정확한 순서를 알 수 있음
            int result = 0;
            for (int i = 1; i <= N; i++) {
                if (listCnt[i] + relistCnt[i] == N - 1) {
                    result++;
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    // DFS 탐색을 통해 자신보다 큰 사람 또는 작은 사람 수를 세기
    static void dfs(int cur, boolean[] visited, int[][] graph, int[] count) {
        visited[cur] = true;

        for (int i = 1; i <= N; i++) {
            if (graph[cur][i] == 1 && !visited[i]) {
                count[i]++;
                dfs(i, visited, graph, count);
            }
        }
    }
}

  
  
