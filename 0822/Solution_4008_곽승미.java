package day0822;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_곽승미 {

    static int maxValue;  // 최댓값 저장
    static int minValue;  // 최솟값 저장
    static int[] num;     // 피연산자 숫자 배열
    static int[] oper = new int[4]; // 연산자 개수 배열 (덧셈, 뺄셈, 곱셈, 나눗셈)

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/s_4008_input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());  // 피연산자 숫자 개수

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                oper[i] = Integer.parseInt(st.nextToken());
            }

            // 피연산자 숫자 입력받기
            num = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
            	num[i] = Integer.parseInt(st.nextToken());
            }

            maxValue = Integer.MIN_VALUE;
            minValue = Integer.MAX_VALUE;

            // 백트래킹 시작 (첫 번째 숫자는 이미 선택되었으므로 index를 1로 시작)
            solve(1, num[0]);

            // 결과 출력
            System.out.println("#" + tc + " " + (maxValue - minValue));
        }

        br.close();
    }

    // 모든 경우의 수를 탐색
    static void solve(int index, int res) {
        // 모든 숫자를 다 사용했으면 최댓값과 최솟값 갱신
        if (index == num.length) {
        	maxValue = Math.max(maxValue, res);
        	minValue = Math.min(minValue, res);
            return;
        }

        // 다음 숫자
        int nextNum = num[index];

        // 덧셈 연산
        if (oper[0] > 0) {
            oper[0] -= 1;
            solve(index + 1, res + nextNum);
            oper[0] += 1;
        }

        // 뺄셈 연산
        if (oper[1] > 0) {
            oper[1] -= 1;
            solve(index + 1, res - nextNum);
            oper[1] += 1;
        }

        // 곱셈 연산
        if (oper[2] > 0) {
            oper[2] -= 1;
            solve(index + 1, res * nextNum);
            oper[2] += 1;
        }

        // 나눗셈 연산
        if (oper[3] > 0) {
            oper[3] -= 1;
            solve(index + 1, res / nextNum);
            oper[3] += 1;
        }
    }
}
