package day0814;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_곽승미 {
    static int N, L;
    static int[] score, cal;
    static int maxscore;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/s_5215_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            score = new int[N];
            cal = new int[N];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                score[n] = Integer.parseInt(st.nextToken());
                cal[n] = Integer.parseInt(st.nextToken());
            }
            maxscore = 0;
            // 모든 경우의 수를 탐색
            combination(0, 0, 0);
            System.out.println("#" + t + " " + maxscore);
        }
    }

    static void combination(int idx, int currScore, int currCal) {
        // 기저 조건: 인덱스가 범위를 벗어나면 종료
        if (idx == N) {
            if (currCal <= L && currScore > maxscore) {
                maxscore = currScore;
            }
            return;
        }

        // 현재 음식을 선택하는 경우
        combination(idx + 1, currScore + score[idx], currCal + cal[idx]);

        // 현재 음식을 선택하지 않는 경우
        combination(idx + 1, currScore, currCal);
    }
}
