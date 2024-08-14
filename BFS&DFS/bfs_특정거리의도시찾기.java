package day0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_특정거리의도시찾기 {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        // 각 도시 연결 정보를 저장할 리스트 배열 초기화
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 모든 도로 정보 입력받아 그래프에 추가
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b); // a번 도시에서 b번 도시로 가는 길이 있음
        }

        // 각 도시에 대한 최단 거리 초기화 (처음엔 모두 -1로 설정)
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        distance[x] = 0; // 출발 도시의 거리는 0으로 설정

        // BFS로 풀이 -  큐 초기화
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);

        // BFS 실행
        while (!q.isEmpty()) {
            int currentCity = q.poll();
            // 현재 도시에서 이동할 수 있는 모든 도시를 확인
            for (int nextCity : graph[currentCity]) {
                if (distance[nextCity] == -1) { // 방문하지 않은 도시라면
                    distance[nextCity] = distance[currentCity] + 1; // 거리 갱신
                    q.offer(nextCity); // 다음 도시를 큐에 추가
                }
            }
        }

        // 최단 거리가 k인 도시들을 출력
        boolean found = false;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == k) {
                System.out.println(i);
                found = true;
            }
        }

        // 만약 거리가 k인 도시가 없다면 -1 출력
        if (!found) {
            System.out.println(-1);
        }
    }

}
