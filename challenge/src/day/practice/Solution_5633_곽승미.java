package day.practice;

import java.io.*;
import java.util.*;

public class Solution_5633_곽승미 {
    // 상하좌우
    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken()); // 세로
            int M = Integer.parseInt(st.nextToken()); // 가로
            int K = Integer.parseInt(st.nextToken()); // 배양 시간

            // 배양판에 이미 세포가 있는지 여부를 저장할 배열 (충분히 큰 크기로 설정)
            boolean[][] v = new boolean[K + N + K][K + M + K];

            // 우선순위 큐: 생명력이 큰 세포가 먼저 처리되도록 설정
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1[2], o2[2]));

            // 세포 정보 입력받기
            for (int i = K; i < K + N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = K; j < K + M; j++) {
                    int L = Integer.parseInt(st.nextToken()); // 세포의 생명력
                    if (L != 0) {  // 생명력이 0이 아니면 세포가 존재함
                        v[i][j] = true; // 해당 위치에 세포가 있음을 표시
                        
                        // 큐에 세포의 정보 추가 (i, j 좌표, 생명력 L, 활성화 상태가 되는 시간 L+L)
                        pq.offer(new int[]{i, j, L, L + L});
                    }
                }
            }

            // K시간 동안 번식 및 상태 갱신
            for (int k = 1; k <= K; k++) {
                // 현재 번식한 세포들을 임시 저장할 큐
                ArrayDeque<int[]> q = new ArrayDeque<>();

                // 우선순위 큐에서 세포 하나씩 처리
                while (!pq.isEmpty()) {
                    int[] ij = pq.poll();
                    ij[3]--; // 활성화까지 남은 시간 감소

                    // 세포가 활성화된 경우
                    if (ij[2] > ij[3]) { // 번식 가능 상태가 됐을 때
                        for (int d = 0; d < 4; d++) { // 상하좌우로 번식 시도
                            int ni = ij[0] + di[d];
                            int nj = ij[1] + dj[d];
                            if (!v[ni][nj]) { // 아직 방문하지 않은 새로운 번식 위치
                                v[ni][nj] = true; // 번식 위치 마킹
                                // 새로운 세포를 큐에 추가
                                q.offer(new int[]{ni, nj, ij[2], ij[2] + ij[2]});
                            }
                        }
                    }

                    // 아직 생명력이 남아있는 세포는 다시 큐에 넣음
                    if (ij[3] > 0) {
                        q.offer(ij);
                    }
                }

                // 새로운 세포들을 우선순위 큐에 다시 삽입
                while (!q.isEmpty()) {
                    pq.offer(q.poll());
                }
            }

            // 최종적으로 살아있는 세포의 수 계산
            sb.append("#").append(tc).append(" ").append(pq.size()).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }
}