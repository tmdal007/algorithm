package day0816;
/*
 * 백준 14888. 연산자 끼워넣기
 * DFS로 풀이
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dfs_연산자끼워넣기 {
    static int N;
    static int[] data;
    static int add, sub, mul, div;
    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE; 

    // DFS 메서드
    public static void dfs(int cnt, int k) {
        // 모든 연산자를 다 사용한 경우, 최솟값과 최댓값 업데이트
        if (cnt == N) {
            minValue = Math.min(minValue, k);
            maxValue = Math.max(maxValue, k);
        } else {
            // 더하기
            if (add > 0) {
                add--;
                dfs(cnt + 1, k + data[cnt]);
                add++;
            }
            // 빼기
            if (sub > 0) {
                sub--;
                dfs(cnt + 1, k - data[cnt]);
                sub++;
            }
            // 곱하기
            if (mul > 0) {
                mul--;
                dfs(cnt + 1, k * data[cnt]);
                mul++;
            }
            // 나누기
            if (div > 0) {
                div--;
                dfs(cnt + 1, k / data[cnt]);
                div++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        // 더하기, 빼기, 곱하기, 나누기
        st = new StringTokenizer(br.readLine());
        add = Integer.parseInt(st.nextToken());
        sub = Integer.parseInt(st.nextToken());
        mul = Integer.parseInt(st.nextToken());
        div = Integer.parseInt(st.nextToken());

        // DFS 실행
        dfs(1, data[0]);

        // 최댓값,최솟값 출력
        System.out.println(maxValue);
        System.out.println(minValue);
    }
}
