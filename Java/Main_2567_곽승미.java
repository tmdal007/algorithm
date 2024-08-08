package algorithm.day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_2567_곽승미 {

    // 2차원배열에서 경계가 바뀌는 부분 카운트(행 기준)
    public static int count(int[][] arr) {
        int cnt = 0;
        for (int[] lst : arr) {
            for (int i = 1; i < lst.length; i++) {
                if (lst[i - 1] != lst[i]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());  // 입력받을 좌표의 개수

        int[][] arr = new int[102][102];  // 102x102 배열 생성

        // 입력받은 좌표에 10x10 크기의 사각형 1로 채우기
        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int si = Integer.parseInt(input[0]);  // 시작 x좌표
            int sj = Integer.parseInt(input[1]);  // 시작 y좌표
            for (int i = si; i < si + 10; i++) {
                for (int j = sj; j < sj + 10; j++) {
                    arr[i][j] = 1; 
                }
            }
        }

        // 배열의 행과 열을 뒤집어 세로 방향의 경계 변화도 생각 
        int[][] arr_t = new int[102][102];
        for (int i = 0; i < 102; i++) {
            for (int j = 0; j < 102; j++) {
                arr_t[i][j] = arr[j][i];
            }
        }

        // 원본 배열과 전치 배열에서 경계 변화를 모두 계산
        int ans = count(arr) + count(arr_t);
        System.out.printf("%d %d",count(arr),count(arr_t));

        // 결과 출력
        System.out.println(ans);
    }
}


