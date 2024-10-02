package day.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3282_곽승미 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder(); 
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 물건 개수
            int K = Integer.parseInt(st.nextToken()); // 최대 가방 무게
            
            int[] w = new int[N+1];
            int[] cost = new int[N+1];
            
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                w[i] = Integer.parseInt(st.nextToken());  // 무게
                cost[i] = Integer.parseInt(st.nextToken()); // 가치
            }
            
            // DP 테이블 생성, dp[i][j]는 i번째 물건까지 고려했을 때 j 무게에서의 최대 가치
            int[][] dp = new int[N+1][K+1];
            
            // DP 계산: 각 물건을 순회하면서, 가방의 무게별로 최적의 값을 찾음
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    // i번째 물건의 무게가 현재 가방 무게를 초과하면 이전 상태 유지
                    if (w[i] > j) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        // 물건을 넣지 않는 경우와 넣는 경우 중 최댓값 선택
                        dp[i][j] = Math.max(dp[i-1][j-w[i]] + cost[i], dp[i-1][j]);
                    }
                }
            }
            
            sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
