package day0822;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_곽승미 {

    static int day, month, threeMonth, year; // 이용권 종류
    static int[] plan;
    static int minCost;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/s_1952_input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            // 이용권 가격 정보 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            day = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken());
            threeMonth = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken());

            // 각 달의 이용 계획 입력 받기
            plan = new int[12];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }

            // 최소 비용을 최대 값(1년권 이용가격)으로 초기화
            minCost = year; 

            cal(0, 0);

            // 결과 출력
            System.out.println("#" + tc + " " + minCost);
        }

        br.close();
    }

    // 최소 비용 계산
    private static void cal(int planmonth, int cost) {
        // 현재 비용이 이미 최소 비용보다 크면 더 이상 탐색하지 않음
    	if(cost >= minCost) {
    		return;
    	}
    	
    	// 12월까지 다 확인했으면 최소 비용 갱신
        if (planmonth >= 12) {
            minCost = Math.min(minCost, cost);
            return;
        }

        // 1일 이용권 사용
        cal(planmonth + 1, cost + plan[planmonth] * day);

        // 1달 이용권 사용
        cal(planmonth + 1, cost + month);

        // 3달 이용권 사용
        if (planmonth <= 9) { // 3달 이용권을 사용할 수 있는 마지막 달은 10월(11,12월 사용 불가)
            cal(planmonth + 3, cost + threeMonth);
        }
    }
}
