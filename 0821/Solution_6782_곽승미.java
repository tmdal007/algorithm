package day0821;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution_6782_곽승미 {
    public static void main(String[] args) throws Exception {
    	//System.setIn(new FileInputStream("res/s_6782_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수 입력

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine()); 
            int cnt = 0;  // 조작 횟수

            while (N > 2) {
                long res = (long) Math.sqrt(N);  // N의 제곱근을 계산

                if (res * res == N) {
                    N = res;  // N이 제곱수라면 제곱근으로 변경
                    cnt++;
                } else {
                    cnt += Math.pow(res + 1, 2) - N;  // 제곱수가 아니면 다음 제곱수로 변경
                    N = (long) Math.pow(res + 1, 2);
                }
            }

            System.out.println("#" + tc + " " + cnt); 
        }
    }
}
